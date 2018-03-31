package com.socailmediaanalyzer.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@PropertySource({"application.properties"})
public class TwitterConfig {

    @Value("${consumerKey}")
    private String consumerKey;

    @Value("${consumerSecret}")
    private String consumerSecret;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${accessTokenSecret}")
    private String accessTokenSecret;

    public Twitter getTwitterTemplate() {
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }
}