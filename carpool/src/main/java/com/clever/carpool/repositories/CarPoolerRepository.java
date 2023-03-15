package com.clever.carpool.repositories;

import com.clever.carpool.entities.CarPoolerEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CarPoolerRepository extends JpaRepository<CarPoolerEntity, Long> {
    CarPoolerEntity findByEmail(String email);
}
