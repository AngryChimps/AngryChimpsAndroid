package com.angrychimps.citizenvet.models.received;

/*
    Used in Service API
 */
public class Service {
    private int id;
    private String name;

    public Service() {
    }

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
}
