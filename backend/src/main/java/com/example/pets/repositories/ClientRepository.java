package com.example.pets.repositories;

import com.example.pets.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {
    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);
}
