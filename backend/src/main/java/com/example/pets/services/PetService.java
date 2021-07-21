package com.example.pets.services;

import com.example.pets.models.Pet;
import com.example.pets.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Optional<Pet> create(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            return Optional.empty();
        }
        if ("".equals(pet.getId()))
            pet.setId(UUID.randomUUID().toString());
        return Optional.ofNullable(petRepository.save(pet));
    }

    public Optional<Pet> read(String id) {
        return petRepository.findById(id);
    }

    public List<Pet> readAll() {
        Iterable<Pet> iterable = petRepository.findAll();
        List<Pet> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public Optional<Pet> update(Pet pet) {
        if (!petRepository.existsById(pet.getId())) {
            return Optional.empty();
        }
        return Optional.ofNullable(petRepository.save(pet));
    }

    public Optional<Pet> delete(String id) {
        Optional<Pet> existing = petRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        petRepository.deleteById(id);
        return existing;
    }
}
