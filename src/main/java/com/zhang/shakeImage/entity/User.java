package com.zhang.shakeImage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhangsl on 2017/2/10.
 */
@Entity
public class User {

    @Id
    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
