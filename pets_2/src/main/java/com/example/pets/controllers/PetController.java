package com.example.pets.controllers;

import com.example.pets.models.Pet;
import com.example.pets.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PetController {

    @Autowired
    private PetRepo petRepo;

    @GetMapping
    public ResponseEntity<Map<String, String>> greeting(){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Hello everyone!!!");
        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        this.petRepo.save(pet);
        return ResponseEntity.ok(pet);
    }
}
