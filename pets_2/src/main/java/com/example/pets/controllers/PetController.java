package com.example.pets.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PetController {

    @GetMapping
    public ResponseEntity<Map<String, String>> greeting(){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Hello everyone!!!");
        return ResponseEntity.ok(map);
    }
}
