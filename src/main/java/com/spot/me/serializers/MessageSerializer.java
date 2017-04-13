package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.Message;
import com.spot.me.entities.User;

import java.util.HashMap;
import java.util.Map;



public class MessageSerializer extends JsonDataSerializer {

    public String getType() {
        return "messages";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Message message = (Message) entity;

        result.put("id", message.getId());
        result.put("message", message.getMessage());
        result.put("author", message.getAuthor().getId());
        result.put("receiver", message.getReceiver().getId());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }


}