package com.socailmediaanalyzer.Repository;

import com.socailmediaanalyzer.DAO.CognitiveDAO;
import com.socailmediaanalyzer.DAO.CognitiveResponseDAO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CognitiveRepo {



    public CognitiveResponseDAO getCognitiveResponse(CognitiveDAO cognitiveDAO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", "9d4dba01ad704412a2cc2945945a83a1");
        HttpEntity<?> request = new HttpEntity<>(cognitiveDAO, headers);
        String resourceUrl = "https://southcentralus.api.cognitive.microsoft.com/text/analytics/v2.0/sentiment";
        ResponseEntity<CognitiveResponseDAO> response = new RestTemplate().postForEntity(resourceUrl, request, CognitiveResponseDAO.class);
        return response.getBody();
    }
}
