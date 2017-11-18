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
    "peg"
})
public class MetadataXmlns {

    @JsonProperty("peg")
    private String peg;

    @JsonProperty("peg")
    public String getPeg() {
        return peg;
    }

    @JsonProperty("peg")
    public void setPeg(String peg) {
        this.peg = peg;
    }
}
