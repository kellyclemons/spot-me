package com.spot.me.utilities;

import com.spot.me.entities.HasId;

/**
 * Created by abbh62 on 4/9/2017.
 */
public class LoginUser implements HasId {
    String id;
    String email;
    String password;

    public LoginUser(String email, String name, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
