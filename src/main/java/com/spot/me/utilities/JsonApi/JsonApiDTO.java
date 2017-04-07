package com.spot.me.utilities.JsonApi;

import com.fasterxml.jackson.annotation.JsonProperty;


public class JsonApiDTO<T> {
    JsonApiDataDTO<T> data;

    public JsonApiDTO(
            @JsonProperty("data") JsonApiDataDTO<T> data) {
        this.data = data;
    }

    public JsonApiDataDTO<T> getData() {
        return this.data;
    }

    public String getType() {
        return this.data.getType();
    }
}