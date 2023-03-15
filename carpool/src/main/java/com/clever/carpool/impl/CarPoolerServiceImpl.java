package com.clever.carpool.impl;

import com.clever.carpool.entities.CarPoolerEntity;
import com.clever.carpool.repositories.CarPoolerRepository;
import com.clever.carpool.services.CarPoolerService;
import com.clever.carpool.to.CarPoolerDto;
import com.clever.carpool.utility.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CarPoolerServiceImpl implements CarPoolerService {

    CarPoolerRepository poolerRepository;
    Utils utils;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CarPoolerDto createPooler(CarPoolerDto pooler) {

        CarPoolerEntity checkPooler =  poolerRepository.findByEmail(pooler.getEmail());
        if(checkPooler != null)
            throw new RuntimeException("Pooler already exist!");

        CarPoolerEntity poolerEntity = new CarPoolerEntity();
        BeanUtils.copyProperties(pooler, poolerEntity);

        poolerEntity.setPoolerId(utils.generateStringId(32));
        poolerEntity.setEncryptedPass(bCryptPasswordEncoder.encode(pooler.getPassword()));

        CarPoolerEntity newPooler = poolerRepository.save(poolerEntity);
        CarPoolerDto poolerDto = new CarPoolerDto();
        BeanUtils.copyProperties(newPooler, poolerDto);
        return poolerDto;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CarPoolerEntity pooler = poolerRepository.findByEmail(email);
        if(pooler != null)
            throw new UsernameNotFoundException(email);
        assert false;
        return new User(pooler.getEmail() ,pooler.getEncryptedPass(),new ArrayList<>());
    }
}
