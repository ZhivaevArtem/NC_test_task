package com.example.pets.controllers;

import com.example.pets.models.Client;
import com.example.pets.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/client", produces = "application/json")
@CrossOrigin("http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // region CRUD
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Optional<Client> c = clientService.create(client);
        if (c.isPresent()) {
            return new ResponseEntity<>(c.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> read(@PathVariable String id) {
        Optional<Client> c = clientService.read(id);
        if (c.isPresent()) {
            return new ResponseEntity<>(c.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> readAll() {
        return ResponseEntity.ok(clientService.readAll());
    }

    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client client) {
        Optional<Client> c = clientService.update(client);
        if (c.isPresent()) {
            return new ResponseEntity<>(c.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable String id) {
        Optional<Client> c = clientService.delete(id);
        if (c.isPresent()) {
            return new ResponseEntity<>(c.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // endregion CRUD
}
