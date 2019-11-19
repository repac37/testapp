package com.repac.model;

import java.io.Serializable;

public class User implements Serializable {

    private static int INDEX = 1;
    private String firstName;
    private String lastName;
    private String email;

    public User(String email) {
        this.email = email;
        this.firstName = "firstName"+INDEX;
        this.lastName = "lastName"+INDEX;
        INDEX++;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
