package com.example.pets.services;

import com.example.pets.models.Client;
import com.example.pets.models.Pet;
import com.example.pets.repositories.ClientRepository;
import com.example.pets.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PetRepository petRepository;

    // region CRUD
    public Optional<Client> create(Client client) {
        if (clientRepository.existsById(client.getId())) {
            return Optional.empty();
        }
        if ("".equals(client.getId()))
            client.setId(UUID.randomUUID().toString());
        return Optional.ofNullable(clientRepository.save(client));
    }

    public Optional<Client> read(String id) {
        return clientRepository.findById(id);
    }

    public List<Client> readAll() {
        Iterable<Client> all = clientRepository.findAll();
        List<Client> list = new ArrayList<>();
        all.forEach(list::add);
        return list;
    }

    public Optional<Client> update(Client client) {
        if (!clientRepository.existsById(client.getId())) {
            return Optional.empty();
        }
        return Optional.ofNullable(clientRepository.save(client));
    }

    public Optional<Client> delete(String id) {
        Optional<Client> existing = clientRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        clientRepository.deleteById(id);
        return existing;
    }
    // endregion CRUD

    public Optional<Pet> addPetToOwner(String clientId, String petId) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (petOpt.isEmpty() || clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Pet pet = petOpt.get();
        Client client = clientOpt.get();
        if ("".equals(pet.getOwnerId()))
            return Optional.empty();
        pet.setOwnerId(client.getId());
        return Optional.ofNullable(petRepository.save(pet));
    }

    public Optional<Pet> removePetFromOwner(String clientId, String petId) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (petOpt.isEmpty() || clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Pet pet = petOpt.get();
        Client client = clientOpt.get();
        if (!client.getId().equals(pet.getOwnerId()))
            return Optional.empty();
        petRepository.deleteById(pet.getId());
        return Optional.of(pet);
    }

    public Optional<Pet> editPetByOwner(String clientId, Pet pet) {
        Optional<Pet> petOpt = petRepository.findById(pet.getId());
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (petOpt.isEmpty() || clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Client client = clientOpt.get();
        if (!client.getId().equals(petOpt.get().getOwnerId()))
            return Optional.empty();
        pet.setOwnerId(client.getId());
        return Optional.ofNullable(petRepository.save(pet));
    }

    public Optional<List<Pet>> readClientsPets(String id) {
        if (clientRepository.existsById(id)) {
            return Optional.of(petRepository.findAllByOwnerId(id));
        }
        return Optional.empty();
    }

    public Optional<Pet> createAndAddPet(String id, Pet pet) {
        if (!clientRepository.existsById(id))
            return Optional.empty();
        pet.setId(UUID.randomUUID().toString());
        pet.setOwnerId(id);
        return Optional.ofNullable(petRepository.save(pet));
    }

    public Optional<Pet> updateClientsPet(String clientId, String petId, Pet pet) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (petOpt.isEmpty() || clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Pet existingPet = petOpt.get();
        Client client = clientOpt.get();
        if (!existingPet.getOwnerId().equals(clientId)) {
            return Optional.empty();
        }
        pet.setId(petId);
        pet.setOwnerId(clientId);
        return Optional.ofNullable(petRepository.save(pet));
    }
}
