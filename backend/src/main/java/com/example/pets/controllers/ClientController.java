package com.example.pets.controllers;

import com.example.pets.models.Client;
import com.example.pets.models.Pet;
import com.example.pets.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/client", produces = "application/json")
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

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> read(@PathVariable String clientId) {
        Optional<Client> c = clientService.read(clientId);
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

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Client> delete(@PathVariable String clientId) {
        Optional<Client> c = clientService.delete(clientId);
        if (c.isPresent()) {
            return new ResponseEntity<>(c.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // endregion CRUD

    @RequestMapping(value = "/{clientId}/pet", method = RequestMethod.GET)
    @PreAuthorize("@authComponent.checkUserIdAndEmail(#clientId, authentication.name)")
    public ResponseEntity<List<Pet>> readClientsPets(@PathVariable String clientId) {
        Optional<List<Pet>> petsOpt = clientService.readClientsPets(clientId);
        if (petsOpt.isPresent()) {
            return ResponseEntity.ok(petsOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clientId}/pet/{petId}")
    @PreAuthorize("@authComponent.checkUserIdAndEmail(#clientId, authentication.name)")
    public ResponseEntity<Pet> readClientSinglePet(@PathVariable String clientId,
                                                   @PathVariable String petId) {
        Optional<Pet> petOpt = clientService.readClientSinglePet(clientId, petId);
        if (petOpt.isPresent()) {
            return ResponseEntity.ok(petOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clientId}/pet")
    @PreAuthorize("@authComponent.checkUserIdAndEmail(#clientId, authentication.name)")
    public ResponseEntity<Pet> createAndAddPet(@PathVariable String clientId,
                                               @RequestBody Pet pet,
                                               Principal principal) {
        Optional<Pet> addedPet = clientService.createAndAddPet(clientId, pet);
        if (addedPet.isPresent()) {
            return ResponseEntity.ok(addedPet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{clientId}/pet/{petId}")
    @PreAuthorize("@authComponent.checkUserIdAndEmail(#clientId, authentication.name)")
    public ResponseEntity<Pet> removeClientsPet(@PathVariable String clientId,
                                                @PathVariable String petId,
                                                Principal principal) {
        Optional<Pet> removedPet = clientService.removePetFromOwner(clientId, petId);
        if (removedPet.isPresent()) {
            return ResponseEntity.ok(removedPet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{clientId}/pet/{petId}")
    @PreAuthorize("@authComponent.checkUserIdAndEmail(#clientId, authentication.name)")
    public ResponseEntity<Pet> updateClientsPet(@PathVariable String clientId,
                                                @PathVariable String petId,
                                                @RequestBody Pet pet,
                                                Principal principal) {
        Optional<Pet> updatedPet = clientService.updateClientsPet(clientId, petId, pet);
        if (updatedPet.isPresent()) {
            return ResponseEntity.ok(updatedPet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
