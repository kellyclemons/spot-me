package com.spot.me.utilities;


/**
 * Created by BHarris on 4/13/17.
 */
public class Messenger{

    private String message;

    private String authorId;

    private String receiverId;

    public Messenger(String message, String authorId, String receiverId) {
        this.message = message;
        this.authorId = authorId;
        this.receiverId = receiverId;
    }

    public Messenger() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
