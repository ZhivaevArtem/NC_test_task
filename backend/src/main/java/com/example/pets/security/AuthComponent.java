package com.example.pets.security;

import com.example.pets.models.Client;
import com.example.pets.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Component
public class AuthComponent {

    @Autowired
    private ClientRepository clientRepository;

    public boolean checkUserIdAndEmail(String clientId, String email) {
//        System.out.println(clientId);
        Optional<Client> userOpt = clientRepository.findById(clientId);
        if (userOpt.isPresent()) {
            return email.equals(userOpt.get().getEmail());
        }
        return false;
    }
}
