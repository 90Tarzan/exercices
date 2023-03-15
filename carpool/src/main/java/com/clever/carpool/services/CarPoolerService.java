package com.clever.carpool.services;

import com.clever.carpool.to.CarPoolerDto;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CarPoolerService extends UserDetailsService {
    CarPoolerDto createPooler(CarPoolerDto pooler);
}
