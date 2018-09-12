package com.namvn.shopping.social.google;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.social.google")

public class GoogleProperties extends SocialProperties{

	
}
