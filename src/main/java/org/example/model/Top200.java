package com.bialy.spotifydatarestapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document
public class Top200 {
    @Id
    private String id;

    @Field(name = "artist")
    private String artist;

    @Field(name = "title")
    private String title;

    @Field(name = "region")
    private String region;

    @Field(name = "date")
    private LocalDateTime date;

    @Field(name = "rank")
    private int rank;

    @Field(name = "streams")
    private int streams;

    @Field(name = "trend")
    private String trend;

    @Field(name = "url")
    private String url;
}
