/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "guid",
    "ownerId",
    "availableDate",
    "expirationDate",
    "ratings",
    "content",
    "restrictionId",
    "availabilityState"
})
public class Media {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("ownerId")
    private String ownerId;
    @JsonProperty("availableDate")
    private Long availableDate;
    @JsonProperty("expirationDate")
    private Long expirationDate;
    @JsonProperty("ratings")
    private List<Object> ratings = null;
    @JsonProperty("content")
    private List<Content> content = null;
    @JsonProperty("restrictionId")
    private String restrictionId;
    @JsonProperty("availabilityState")
    private String availabilityState;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("ownerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("ownerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("availableDate")
    public Long getAvailableDate() {
        return availableDate;
    }

    @JsonProperty("availableDate")
    public void setAvailableDate(Long availableDate) {
        this.availableDate = availableDate;
    }

    @JsonProperty("expirationDate")
    public Long getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("ratings")
    public List<Object> getRatings() {
        return ratings;
    }

    @JsonProperty("ratings")
    public void setRatings(List<Object> ratings) {
        this.ratings = ratings;
    }

    @JsonProperty("content")
    public List<Content> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<Content> content) {
        this.content = content;
    }

    @JsonProperty("restrictionId")
    public String getRestrictionId() {
        return restrictionId;
    }

    @JsonProperty("restrictionId")
    public void setRestrictionId(String restrictionId) {
        this.restrictionId = restrictionId;
    }

    @JsonProperty("availabilityState")
    public String getAvailabilityState() {
        return availabilityState;
    }

    @JsonProperty("availabilityState")
    public void setAvailabilityState(String availabilityState) {
        this.availabilityState = availabilityState;
    }

}
