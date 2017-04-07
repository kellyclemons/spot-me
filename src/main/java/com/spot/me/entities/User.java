package com.spot.me.entities;

import com.spot.me.utilities.PasswordStorage;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String password;


    public User() {
    }

    public User(String email, String name, String password) throws PasswordStorage.CannotPerformOperationException {
        this.email = email;
        this.name = name;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordStorage.CannotPerformOperationException {
        this.password = PasswordStorage.createHash(password);
    }

    public boolean verifyPassword(String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {

        return PasswordStorage.verifyPassword(password, getPasswordHash());
    }

    public String getPasswordHash() {
        return password;
    }
}
