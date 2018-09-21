package com.namvn.shopping.config.view;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Class used to config view abc.html...(web)
 * Base on{@code org.springframework.web.WebMvcConfigurer} interface
 *
 * @see how to add view(.html) to controler
 */
@Configuration
@ComponentScan(basePackages = {"com.namvn.shopping.web"})
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

        registry.addViewController("/index.html");
        registry.addViewController("/404.html");
        registry.addViewController("/blog.html");
        registry.addViewController("/blog-single.html");
        registry.addViewController("/cart.html");
        registry.addViewController("/checkout.html");
        registry.addViewController("/contact-us.html");
        registry.addViewController("/product-details.html");
        registry.addViewController("/shop.html");
//        registry.addViewController("/login");
//        registry.addViewController("/login");
//        registry.addViewController("/login");
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
    }
}
