
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
    "$xmlns",
    "startIndex",
    "itemsPerPage",
    "entryCount",
    "title",
    "entries"
})
public class VideoMetaData {

    @JsonProperty("$xmlns")
    private com.splay.content.model.MetadataXmlns $xmlns;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("itemsPerPage")
    private Integer itemsPerPage;
    @JsonProperty("entryCount")
    private Integer entryCount;
    @JsonProperty("title")
    private String title;
    @JsonProperty("entries")
    private List<Entry> entries = null;

    @JsonProperty("$xmlns")
    public com.splay.content.model.MetadataXmlns get$xmlns() {
        return $xmlns;
    }

    @JsonProperty("$xmlns")
    public void set$xmlns(com.splay.content.model.MetadataXmlns $xmlns) {
        this.$xmlns = $xmlns;
    }

    @JsonProperty("startIndex")
    public Integer getStartIndex() {
        return startIndex;
    }

    @JsonProperty("startIndex")
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @JsonProperty("itemsPerPage")
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    @JsonProperty("itemsPerPage")
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @JsonProperty("entryCount")
    public Integer getEntryCount() {
        return entryCount;
    }

    @JsonProperty("entryCount")
    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("entries")
    public List<Entry> getEntries() {
        return entries;
    }

    @JsonProperty("entries")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
