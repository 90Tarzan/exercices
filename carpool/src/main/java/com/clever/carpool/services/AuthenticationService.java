package com.clever.carpool.services;

import com.clever.carpool.requests.AuthenticationRequest;
import com.clever.carpool.requests.RegisterRequest;
import com.clever.carpool.responses.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request) ;

}
