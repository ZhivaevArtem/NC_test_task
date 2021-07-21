package com.example.pets.services;

import com.example.pets.models.Pet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PetServiceListImpl implements PetService {

    // TODO: replace with DB
    private List<Pet> pets = new LinkedList<>();

    @Override
    public Optional<Pet> create(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(UUID.randomUUID().toString());
        }
        if (pets.stream().anyMatch(p -> pet.getId().equals(p.getId()))) {
            return Optional.empty();
        } else {
            pets.add(pet);
            return Optional.of(pet);
        }
    }

    @Override
    public Optional<Pet> read(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return pets.stream().filter(p -> uuid.toString().equals(p.getId())).findAny();
    }

    @Override
    public List<Pet> readAll() {
        return this.pets;
    }

    @Override
    public Optional<Pet> update(Pet pet) {
        Optional<Pet> existingPet = pets.stream().filter(p -> pet.getId().equals(p.getId())).findAny();
        existingPet.ifPresent(e -> {
            e.setName(pet.getName());
            e.setType(pet.getType());
        });
        return existingPet;
    }

    @Override
    public Optional<Pet> delete(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        Optional<Pet> any = pets.stream().filter(p -> uuid.toString().equals(p.getId())).findAny();
        any.ifPresent(pets::remove);
        return any;
    }
}
