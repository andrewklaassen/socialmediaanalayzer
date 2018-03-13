package com.socailmediaanalyzer.Repository;

import com.socailmediaanalyzer.Configuration.TwitterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TwitterRepo {

    private TwitterConfig twitterConfig;

    @Autowired
    public TwitterRepo(TwitterConfig twitterConfig){
        this.twitterConfig = twitterConfig;
    }

    public List<Tweet> getTweets(String textToSearch) {
        Twitter twitter = twitterConfig.getTwitterTemplate();
        SearchResults searchResults = twitter.searchOperations().search(textToSearch);

        return searchResults.getTweets();
    }
}
