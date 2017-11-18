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

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    "characterName",
    "creditType",
    "isInactive",
    "order",
    "personId",
    "personName"
})
public class Credit {

    @JsonProperty("characterName")
    private String characterName;
    @JsonProperty("creditType")
    private String creditType;
    @JsonProperty("isInactive")
    private Boolean isInactive;
    @JsonProperty("order")
    private Integer order;
    @JsonProperty("personId")
    private String personId;
    @JsonProperty("personName")
    private String personName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("characterName")
    public String getCharacterName() {
        return characterName;
    }

    @JsonProperty("characterName")
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @JsonProperty("creditType")
    public String getCreditType() {
        return creditType;
    }

    @JsonProperty("creditType")
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    @JsonProperty("isInactive")
    public Boolean getIsInactive() {
        return isInactive;
    }

    @JsonProperty("isInactive")
    public void setIsInactive(Boolean isInactive) {
        this.isInactive = isInactive;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("personId")
    public String getPersonId() {
        return personId;
    }

    @JsonProperty("personId")
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @JsonProperty("personName")
    public String getPersonName() {
        return personName;
    }

    @JsonProperty("personName")
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
