package com.example.pets.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Pet {
    @Id
    private String id = "";
    private String name = "";
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.UNKNOWN;
    private Date birth = new Date();
    private String breed = "";
    private String color = "";
    private String description = "";
    private String animalType = "";
    private String ownerId = "";

    // region constructors
    public Pet() {
    }

    public Pet(String id, String name, Sex sex, Date birth,
               String breed, String color, String description, String animalType, String ownerId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.breed = breed;
        this.color = color;
        this.description = description;
        this.animalType = animalType;
        this.ownerId = ownerId;
    }
    // endregion constructors

    // region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    // endregion getters and setters
}
