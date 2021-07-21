package com.example.pets.services;

import com.example.pets.models.AuthResponse;
import com.example.pets.models.Client;
import com.example.pets.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Optional<AuthResponse> signUp(Client client) {
        if ("".equals(client.getEmail()) ||
            "".equals(client.getPassword()) ||
            clientRepository.existsByEmail(client.getEmail())) {
            return Optional.empty();
        }
        client.setId(UUID.randomUUID().toString());
        Client saved = clientRepository.save(client);
        saved.setPassword("");
        return Optional.of(
            new AuthResponse(saved, "")
        );
    }

    public Optional<AuthResponse> signIn(String login, String password) {
        Optional<Client> user = clientRepository.findByEmail(login);
        if (user.isPresent()) {
            if (passwordEncoder.matches(user.get().getPassword(), password)) {
                return Optional.of(new AuthResponse(user.get(), ""));
            }
        }
        return Optional.empty();
    }
}
