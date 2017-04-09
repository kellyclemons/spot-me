package com.spot.me.Parers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonApiDataParser<T> {
    String type;
    String id;
    T attributes;

    public JsonApiDataParser(
            @JsonProperty("type") String type,
            @JsonProperty("id") String id,
            @JsonProperty("attributes") T attributes
    ) {
        this.type = type;
        this.id = id;
        this.attributes = attributes;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public T getEntity() {
        return this.attributes;
    }
}