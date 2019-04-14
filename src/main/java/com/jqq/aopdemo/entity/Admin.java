package com.jqq.aopdemo.entity;

import org.springframework.stereotype.Component;

public class Admin {
    int id;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
