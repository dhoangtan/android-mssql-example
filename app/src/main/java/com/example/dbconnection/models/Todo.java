package com.example.dbconnection.models;

import java.io.Serializable;

public class Todo implements Serializable {
    private int id;
    private String title;
    private String description;

    public Todo() {

    }

    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
