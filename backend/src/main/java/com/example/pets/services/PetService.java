package com.example.pets.services;

import com.example.pets.models.Pet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("petService")
public class PetService {

    // TODO: replace with DB
    private List<Pet> pets = new LinkedList<>();

    public Optional<Pet> create(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(UUID.randomUUID());
        }
        if (pets.stream().anyMatch(p -> pet.getId().equals(p.getId()))) {
            return Optional.empty();
        } else {
            pets.add(pet);
            return Optional.of(pet);
        }
    }

    public Optional<Pet> get(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return pets.stream().filter(p -> uuid.equals(p.getId())).findAny();
    }

    public List<Pet> getAll() {
        return this.pets;
    }

    public Optional<Pet> update(Pet pet) {
        Optional<Pet> existingPet = pets.stream().filter(p -> pet.getId().equals(p.getId())).findAny();
        existingPet.ifPresent(e -> {
            e.setName(pet.getName());
            e.setType(pet.getType());
        });
        return existingPet;
    }

    public Optional<Pet> delete(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        Optional<Pet> any = pets.stream().filter(p -> uuid.equals(p.getId())).findAny();
        any.ifPresent(pets::remove);
        return any;
    }
}
