package com.example.countryborders.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name{
    public String common;
    public String official;
    @JsonProperty("native")
    public Native mynative;
}