package com.socailmediaanalyzer.Service;

import com.socailmediaanalyzer.DAO.CognitiveResponseDAO;
import com.socailmediaanalyzer.Repository.CognitiveRepo;
import com.socailmediaanalyzer.DAO.CognitiveDAO;
import com.socailmediaanalyzer.DAO.Document;
import com.socailmediaanalyzer.DAO.Sentiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CognitiveService {

    private CognitiveRepo cognitiveRepo;

    @Autowired
    public CognitiveService(CognitiveRepo cognitiveRepo) {
        this.cognitiveRepo = cognitiveRepo;
    }

    public double getSentimentAverage(List<Sentiment> sentiments) {
        CognitiveDAO cognitiveDAO = new CognitiveDAO();
        cognitiveDAO.setDocuments(transformSentimentsToDocuments(sentiments));
        CognitiveResponseDAO cognitiveResponseDAO = cognitiveRepo.getCognitiveResponse(cognitiveDAO);
        double sum = cognitiveResponseDAO.getDocuments().stream().mapToDouble(document->document.getScore()).sum();
        return sum / cognitiveResponseDAO.getDocuments().size();
    }

    public Sentiment getLowestRatedTweet(List<Sentiment> sentiments) {
        CognitiveDAO cognitiveDAO = new CognitiveDAO();
        cognitiveDAO.setDocuments(transformSentimentsToDocuments(sentiments));
        CognitiveResponseDAO cognitiveResponseDAO = cognitiveRepo.getCognitiveResponse(cognitiveDAO);

        Sentiment returner = sentiments.get(0);
        for(int i = 0; i < cognitiveResponseDAO.getDocuments().size(); i++) {
            sentiments.get(i).setId(Integer.toString(i+1));
            sentiments.get(i).setRating(cognitiveResponseDAO.getDocuments().get(i).getScore());
            log.info("" + sentiments.get(i).getRating());

            if(cognitiveResponseDAO.getDocuments().get(i).getScore() < returner.getRating()) {
                returner = sentiments.get(i);
            }
        }
        return returner;
    }

    public Sentiment getHighestRatedTweet(List<Sentiment> sentiments) {
        CognitiveDAO cognitiveDAO = new CognitiveDAO();
        cognitiveDAO.setDocuments(transformSentimentsToDocuments(sentiments));
        CognitiveResponseDAO cognitiveResponseDAO = cognitiveRepo.getCognitiveResponse(cognitiveDAO);

        Sentiment returner = sentiments.get(0);
        for(int i = 0; i < cognitiveResponseDAO.getDocuments().size(); i++) {
            sentiments.get(i).setId(Integer.toString(i+1));
            sentiments.get(i).setRating(cognitiveResponseDAO.getDocuments().get(i).getScore());
            log.info("" + cognitiveResponseDAO.getDocuments().get(i).getScore());

            if(cognitiveResponseDAO.getDocuments().get(i).getScore() > returner.getRating()) {
                returner = sentiments.get(i);
            }
        }
        return returner;
    }

    public List<Sentiment> getAllTweets(List<Sentiment> sentiments) {
        CognitiveDAO cognitiveDAO = new CognitiveDAO();
        cognitiveDAO.setDocuments(transformSentimentsToDocuments(sentiments));
        CognitiveResponseDAO cognitiveResponseDAO = cognitiveRepo.getCognitiveResponse(cognitiveDAO);

        for(int i = 0; i < cognitiveResponseDAO.getDocuments().size(); i++) {
            log.info("" + cognitiveResponseDAO.getDocuments().get(i).getScore());
            sentiments.get(i).setId(Integer.toString(i+1));
            sentiments.get(i).setRating(cognitiveResponseDAO.getDocuments().get(i).getScore());
        }

        return sentiments;
    }

    private List<Document> transformSentimentsToDocuments(List<Sentiment> sentiments) {
        List<Document> documents = new ArrayList<>();
        for(int i =1; i <= sentiments.size(); i++) {
            Document document = new Document();
            document.setLanguage("en");
            document.setId(Integer.toString(i));
            document.setText(sentiments.get(i-1).getText());
            documents.add(document);
        }
        return documents;
    }
}