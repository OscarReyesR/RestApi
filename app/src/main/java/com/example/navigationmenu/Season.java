package com.example.navigationmenu;

import retrofit2.http.Body;

public class Season {
    String name;
    String description;
    boolean status;

    public Season(){}

    public Season(String name, String description, boolean status)
    {
        this.name=name;
        this.description=description;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
