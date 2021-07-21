package com.example.pets.services;

import com.example.pets.models.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PetService {
    // region CRUD
    Optional<Pet> create(Pet pet);
    Optional<Pet> read(String id);
    List<Pet> readAll();
    Optional<Pet> update(Pet pet);
    Optional<Pet> delete(String id);
    // endregion CRUD
}
