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
    "thumb-615x1536",
    "thumb-613x1536",
    "thumb-677x474",
    "thumb-614x1536",
    "thumbc-615x1536",
    "thumbc-613x1536",
    "thumbc-677x474",
    "thumbc-614x1536"
})
public class Thumbnails {

    @JsonProperty("thumb-615x1536")
    private ThumbNail thumb615x1536;
    @JsonProperty("thumb-613x1536")
    private ThumbNail thumb613x1536;
    @JsonProperty("thumb-677x474")
    private ThumbNail thumb677x474;
    @JsonProperty("thumb-614x1536")
    private ThumbNail thumb614x1536;
    @JsonProperty("thumbc-615x1536")
    private ThumbNail thumbc615x1536;
    @JsonProperty("thumbc-613x1536")
    private ThumbNail thumbc613x1536;
    @JsonProperty("thumbc-677x474")
    private ThumbNail thumbc677x474;
    @JsonProperty("thumbc-614x1536")
    private ThumbNail thumbc614x1536;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("thumb-615x1536")
    public ThumbNail getThumb615x1536() {
        return thumb615x1536;
    }

    @JsonProperty("thumb-615x1536")
    public void setThumb615x1536(ThumbNail thumb615x1536) {
        this.thumb615x1536 = thumb615x1536;
    }

    @JsonProperty("thumb-613x1536")
    public ThumbNail getThumb613x1536() {
        return thumb613x1536;
    }

    @JsonProperty("thumb-613x1536")
    public void setThumb613x1536(ThumbNail thumb613x1536) {
        this.thumb613x1536 = thumb613x1536;
    }

    @JsonProperty("thumb-677x474")
    public ThumbNail getThumb677x474() {
        return thumb677x474;
    }

    @JsonProperty("thumb-677x474")
    public void setThumb677x474(ThumbNail thumb677x474) {
        this.thumb677x474 = thumb677x474;
    }

    @JsonProperty("thumb-614x1536")
    public ThumbNail getThumb614x1536() {
        return thumb614x1536;
    }

    @JsonProperty("thumb-614x1536")
    public void setThumb614x1536(ThumbNail thumb614x1536) {
        this.thumb614x1536 = thumb614x1536;
    }

    @JsonProperty("thumbc-615x1536")
    public ThumbNail getThumbc615x1536() {
        return thumbc615x1536;
    }

    @JsonProperty("thumbc-615x1536")
    public void setThumbc615x1536(ThumbNail thumbc615x1536) {
        this.thumbc615x1536 = thumbc615x1536;
    }

    @JsonProperty("thumbc-613x1536")
    public ThumbNail getThumbc613x1536() {
        return thumbc613x1536;
    }

    @JsonProperty("thumbc-613x1536")
    public void setThumbc613x1536(ThumbNail thumbc613x1536) {
        this.thumbc613x1536 = thumbc613x1536;
    }

    @JsonProperty("thumbc-677x474")
    public ThumbNail getThumbc677x474() {
        return thumbc677x474;
    }

    @JsonProperty("thumbc-677x474")
    public void setThumbc677x474(ThumbNail thumbc677x474) {
        this.thumbc677x474 = thumbc677x474;
    }

    @JsonProperty("thumbc-614x1536")
    public ThumbNail getThumbc614x1536() {
        return thumbc614x1536;
    }

    @JsonProperty("thumbc-614x1536")
    public void setThumbc614x1536(ThumbNail thumbc614x1536) {
        this.thumbc614x1536 = thumbc614x1536;
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
