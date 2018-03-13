package com.socailmediaanalyzer.Service;

import com.socailmediaanalyzer.DAO.Sentiment;
import com.socailmediaanalyzer.Repository.TwitterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AggregatorService {

    private TwitterRepo twitterRepo;

    @Autowired
    public AggregatorService(TwitterRepo twitterRepo){
        this.twitterRepo = twitterRepo;
    }

    @Deprecated
    public List<String> getTexts(String textToSearch) {
        List<Tweet> tweets = twitterRepo.getTweets(textToSearch);
        List<String> texts = new ArrayList<>();

        for(Tweet tweet : tweets) {
            texts.add(tweet.getText());
            log.info(tweet.getText());
        }

        return texts;
    }

    public List<Sentiment> getSentiments(String textToSearch) {
        List<Tweet> tweets = twitterRepo.getTweets(textToSearch);
        List<Sentiment> sentimentList = new ArrayList<>();

        for(Tweet tweet : tweets) {
            log.info(tweet.getUser().getScreenName() + " :: " + tweet.getText());
            sentimentList.add(new Sentiment(
                    "",
                    -1,
                    tweet.getCreatedAt(),
                    tweet.getText(),
                    tweet.getLanguageCode(),
                    tweet.getSource(),
                    "@" + tweet.getUser().getScreenName(),
                    tweet.getUser().getName(),
                    tweet.getUser().getDescription()));
        }
        return sentimentList;
    }

    public List<Tweet> getTweets(String textToSearch) {
        return twitterRepo.getTweets(textToSearch);
    }
}
