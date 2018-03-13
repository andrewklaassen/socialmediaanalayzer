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
    private String consumerKey;// = "GFlUcD0WCcggqoorazU58bW1o";

    @Value("${consumerSecret}")
    private String consumerSecret;// = "jGt5XfzMCOioLrmoOd7pUZGSK1OEgSCXhG6qsYnyfRwpcoz1Gi";

    @Value("${accessToken}")
    private String accessToken;// = "971456480326144000-p2BempWJerVwO1AtkmY1nlL2TNYIPyy";

    @Value("${accessTokenSecret}")
    private String accessTokenSecret;// = "fbcVlavyow0jbiEPsI619KxT1rrZgqr3ntHElzp6C5esK";

    public Twitter getTwitterTemplate() {
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }
}