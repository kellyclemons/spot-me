package com.spot.me.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="friends")
public class Friend implements HasId {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column
    private int status;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User requestee;

    @Transient
    @JsonProperty("sender-id")
    private String requesteridNum;

    @Transient
    @JsonProperty("receiver-id")
    private String requesteeIdNum;

    public Friend(int status, User requester, User requestee) {
        this.status = status;
        this.requester = requester;
        this.requestee = requestee;
    }

    public Friend() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getRequestee() {
        return requestee;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequesteridNum() {
        return requesteridNum;
    }

    public void setRequesteridNum(String requesteridNum) {
        this.requesteridNum = requesteridNum;
    }

    public String getRequesteeIdNum() {
        return requesteeIdNum;
    }

    public void setRequesteeIdNum(String requesteeIdNum) {
        this.requesteeIdNum = requesteeIdNum;
    }
}
