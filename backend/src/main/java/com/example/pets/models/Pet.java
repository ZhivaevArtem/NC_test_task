package com.example.pets.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "_pet")
public class Pet {
    @Id
    private String id = new UUID(0L, 0L).toString();
    private String type = "";
    private String name = "";

    // region: constructors
    public Pet() {}

    public Pet(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

//    public Pet(String id, String type, String name) {
//        this(UUID.fromString(id), type, name);
//    }

    public Pet(String type, String name) {
        this(new UUID(0L, 0L).toString(), type, name);
    }
    // endregion: constructors

    // region: getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
