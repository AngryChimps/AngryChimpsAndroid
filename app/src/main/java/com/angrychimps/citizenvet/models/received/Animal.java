package com.angrychimps.citizenvet.models.received;

/*
    Received from Animal API GET
 */
public class Animal {
    private int id;
    private String name;

    public Animal() {
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
