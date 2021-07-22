package com.example.pets.repositories;

import com.example.pets.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
    List<Pet> findAllByOwnerId(String ownerId);
}
