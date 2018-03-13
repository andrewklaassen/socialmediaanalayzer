package com.socailmediaanalyzer.DAO;

import lombok.Data;

import java.util.List;

@Data
public class CognitiveResponseDAO {
    private List<DocumentResponse> documents;
    private List<String> errors;
}
