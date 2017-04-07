package com.spot.me.utilities.JsonApi;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonApiDataDTO<T> {
    public String type;

    public String id;

    T attributes;
    Map<String, JsonRelationDTO> relationships;

    public JsonApiDataDTO(
            @JsonProperty("type") String type,
            @JsonProperty("id") String id,
            @JsonProperty("attributes") T attributes,
            @JsonProperty("relationships") Map<String, JsonRelationDTO> relationships
    ) {
        this.type = type;
        this.id = id;
        this.attributes = attributes;
        this.relationships = relationships;
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

    public String getRelationId(String key) {
        return this.relationships.get(key).getId();
    }
}