package com.example.pets.controllers;

import com.example.pets.models.AuthResponse;
import com.example.pets.models.Client;
import com.example.pets.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth",
    produces = "application/json")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody Client client) {
        Optional<AuthResponse> authResponse = authService.signUp(client);
        if (authResponse.isPresent()) {
            return new ResponseEntity<>(authResponse.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<AuthResponse> signIn(@RequestParam String email,
                                               @RequestParam String password) {
        Optional<AuthResponse> authResponse = authService.signIn(email, password);
        if (authResponse.isPresent()) {
            return new ResponseEntity<>(authResponse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/user_info")
    public ResponseEntity<AuthResponse> userInfo(Principal principal) {
        Optional<AuthResponse> authResponse = authService.signIn(principal.getName());
        if (authResponse.isPresent()) {
            return new ResponseEntity<>(authResponse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
