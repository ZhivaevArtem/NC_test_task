package com.example.pets.services;

import com.example.pets.models.Client;
import com.example.pets.repositories.ClientRepository;
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
}
