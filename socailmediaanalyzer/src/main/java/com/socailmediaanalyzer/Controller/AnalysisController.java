package com.socailmediaanalyzer.Controller;
import com.socailmediaanalyzer.DAO.Sentiment;
import com.socailmediaanalyzer.Response.ErrorSeverity;
import com.socailmediaanalyzer.Response.ServiceError;
import com.socailmediaanalyzer.Response.ServiceResponse;
import com.socailmediaanalyzer.Service.AggregatorService;
import com.socailmediaanalyzer.Service.CognitiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
class AnalysisController {

    private CognitiveService cognitiveService;
    private AggregatorService aggregatorService;

    @Autowired
    public AnalysisController (CognitiveService cognitiveService, AggregatorService aggregatorService) {
        this.cognitiveService = cognitiveService;
        this.aggregatorService = aggregatorService;
    }

    @RequestMapping("/api/getSentimentAverage/{textToSearch}")
    public ServiceResponse<Double> getSentimentAverage(@PathVariable String textToSearch) {
        try {
            List<Sentiment> sentiments = aggregatorService.getSentiments("#" + textToSearch);
            double average = cognitiveService.getSentimentAverage(sentiments);
            return new ServiceResponse<>(average, true, 1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ServiceResponse<>(null, false, 0, Arrays.asList(new ServiceError(ErrorSeverity.ERROR, e.getMessage())));
        }
    }

    @RequestMapping("/api/getLowestRatedTweet/{textToSearch}")
    public ServiceResponse<Sentiment> getLowestRatedTweet(@PathVariable String textToSearch) {
        try {
            List<Sentiment> listOfTweets = aggregatorService.getSentiments("#" + textToSearch);
            Sentiment lowestRatedTweet = cognitiveService.getLowestRatedTweet(listOfTweets);
            return new ServiceResponse<>(lowestRatedTweet, true, 1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ServiceResponse<>(null, false, 0, Arrays.asList(new ServiceError(ErrorSeverity.ERROR, e.getMessage())));
        }
    }

    @RequestMapping("/api/getHighestRatedTweet/{textToSearch}")
    public ServiceResponse<Sentiment> getHighestRatedTweet(@PathVariable String textToSearch) {
        try {
            List<Sentiment> listOfTweets = aggregatorService.getSentiments("#" + textToSearch);
            Sentiment highestRatedTweet = cognitiveService.getHighestRatedTweet(listOfTweets);
            return new ServiceResponse<>(highestRatedTweet, true, 1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ServiceResponse<>(null, false, 0, Arrays.asList(new ServiceError(ErrorSeverity.ERROR, e.getMessage())));
        }
    }

    @RequestMapping("/api/getAllTweets/{textToSearch}")
    public ServiceResponse<List<Sentiment>> getAllTweets(@PathVariable String textToSearch) {
        try {
            List<Sentiment> sentiments = aggregatorService.getSentiments("#" + textToSearch);
            List<Sentiment> ratedSentiments = cognitiveService.getAllTweets(sentiments);
            log.debug(ratedSentiments.toString());
            return new ServiceResponse<>(ratedSentiments, true, ratedSentiments.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ServiceResponse<>(null, false, 0, Arrays.asList(new ServiceError(ErrorSeverity.ERROR, e.getMessage())));
        }
    }

}