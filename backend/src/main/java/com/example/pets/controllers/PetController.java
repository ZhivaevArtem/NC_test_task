package com.example.pets.controllers;

import com.example.pets.models.Pet;
import com.example.pets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pet")
@CrossOrigin("http://localhost:4200")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    // region: CRUD
    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        Optional<Pet> opt = petService.create(pet);
        if (opt.isPresent()) {
            return new ResponseEntity<>(pet, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> read(@PathVariable("id") String id) {
        Optional<Pet> opt = petService.get(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pet>> readAll() {
        return new ResponseEntity<>(petService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        Optional<Pet> opt = petService.update(pet);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> delete(@PathVariable("id") String id) {
        Optional<Pet> opt = petService.delete(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // endregion: CRUD
}
