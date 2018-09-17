package com.namvn.shopping.config.security;

import com.namvn.shopping.security.CustomAuthenticationProvider;
import com.namvn.shopping.security.CustomRememberMeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.sql.DataSource;

//@formatter:off
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().
                antMatchers("/css/**", "/connect/**").permitAll()
                .antMatchers("/secure/**")

                .authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/secure/user")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/invalidSession.html")
                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none()
                .and()

                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/logout.html?logSucc=true")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .rememberMe().rememberMeServices(rememberMeServices()).key("theKey");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    //	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)
//				.passwordEncoder(bCryptPasswordEncoder())
//				.and()
//				.authenticationProvider(authenticationProvider())
//				.jdbcAuthentication()
//				.dataSource(dataSource);
//	}
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        CustomRememberMeServices rememberMeServices = new CustomRememberMeServices("theKey", userDetailsService, new InMemoryTokenRepositoryImpl());
        return rememberMeServices;
    }
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}

}

// @formatter:on