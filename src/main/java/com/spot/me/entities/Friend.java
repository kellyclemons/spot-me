package com.spot.me.entities;

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

    public Friend(int status, User requester, User requestee) {
        this.status = status;
        this.requester = requester;
        this.requestee = requestee;
    }

    @Transient
    private String requesteridNum;

    @Transient
    private String requesteeIdNum;

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
