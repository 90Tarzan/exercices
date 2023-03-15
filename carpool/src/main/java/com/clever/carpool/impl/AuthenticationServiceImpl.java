package com.clever.carpool.impl;

import com.clever.carpool.entities.CarPoolerEntity;
import com.clever.carpool.entities.constants.PoolerRole;
import com.clever.carpool.repositories.CarPoolerRepository;
import com.clever.carpool.requests.AuthenticationRequest;
import com.clever.carpool.requests.RegisterRequest;
import com.clever.carpool.responses.AuthenticationResponse;
import com.clever.carpool.security.JwtService;
import com.clever.carpool.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CarPoolerRepository poolerRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var pooler = CarPoolerEntity.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .encryptedPass(passwordEncoder.encode(request.getPassword()))
                .role(PoolerRole.USER)
                .build();
        var savedPooler = poolerRepository.save(pooler);
        var jwtToken = jwtService.generateToken(pooler);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var pooler = poolerRepository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(pooler);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
