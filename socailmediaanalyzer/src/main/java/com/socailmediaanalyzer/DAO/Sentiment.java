package com.socailmediaanalyzer.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Sentiment {
    String id;
    double rating;
    Date createdAt;
    String text;
    String language;
    String source;
    String userName;
    String userDisplayName;
    String userDescription;
}
