package com.example.pets.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    @GetMapping
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("Hello everyone!!!");
    }
}
