package com.example.jsimplerest;

import java.util.UUID;

public class Pet {

    private String id;
    private String name;

    public Pet() {
    }

    public Pet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pet(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
