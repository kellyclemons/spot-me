package com.spot.me.utilities.JsonApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRelationDTO {
    JsonRelationDataDTO data;

    public JsonRelationDTO(@JsonProperty("data") JsonRelationDataDTO data) {
        this.data = data;
    }

    String getId() {
        return this.data.getId();
    }

    String getType() {
        return this.data.getType();
    }


    static class JsonRelationDataDTO {
        String type;
        String id;

        public JsonRelationDataDTO(@JsonProperty("type") String type, @JsonProperty("id") String id) {
            this.type = type;
            this.id = id;
        }

        String getId() {
            return this.id;
        }

        String getType() {
            return this.type;
        }
    }
}
