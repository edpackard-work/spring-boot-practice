package com.example.domain;

public class User {
    private int id;
    private String name;
    private boolean active;

    public User (int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive(boolean active) {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
