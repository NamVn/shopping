package com.namvn.shopping.social.providers;

import com.namvn.shopping.autologin.Autologin;
import com.namvn.shopping.persistence.model.User;
import com.namvn.shopping.persistence.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.thymeleaf.util.StringUtils;


@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    private Google google;

    private ConnectionRepository connectionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected Autologin autologin;

    public BaseProvider(Facebook facebook, Google google , ConnectionRepository connectionRepository) {
	this.facebook = facebook;
	this.connectionRepository = connectionRepository;
	this.google = google;

    }

    protected void saveUserDetails(User userBean) {
	if (StringUtils.isEmpty(userBean.getPassword())) {
	    userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
	}
	userRepository.save(userBean);

    }

    public void autoLoginUser(User userBean) {
	autologin.setSecuritycontext(userBean);
    }

    public Facebook getFacebook() {
	return facebook;
    }

    public void setFacebook(Facebook facebook) {
	this.facebook = facebook;
    }

    public ConnectionRepository getConnectionRepository() {
	return connectionRepository;
    }

    public void setConnectionRepository(ConnectionRepository connectionRepository) {
	this.connectionRepository = connectionRepository;
    }

    public Google getGoogle() {
	return google;
    }

    public void setGoogle(Google google) {
	this.google = google;
    }



}
