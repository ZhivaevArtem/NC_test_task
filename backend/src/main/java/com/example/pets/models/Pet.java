package com.example.pets.models;

import java.util.UUID;

public class Pet {
    private UUID id = UUID.randomUUID();
    private String type = "";
    private String name = "";

    // region: constructors
    public Pet() {}

    public Pet(UUID id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Pet(String id, String type, String name) {
        this(UUID.fromString(id), type, name);
    }

    public Pet(String type, String name) {
        this(UUID.randomUUID(), type, name);
    }
    // endregion: constructors

    // region: getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // endregion: getters and setters
}
