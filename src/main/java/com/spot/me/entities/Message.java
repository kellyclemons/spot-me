package com.spot.me.entities;
import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String message;

    @ManyToOne
    private User author;

    @ManyToOne
    private User receiver;

    public Message(String message, User author) {
        this.message = message;
        this.author = author;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
