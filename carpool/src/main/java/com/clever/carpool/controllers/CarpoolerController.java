package com.clever.carpool.controllers;

import com.clever.carpool.repositories.CarPoolerRepository;
import com.clever.carpool.requests.CarPoolerRequest;
import com.clever.carpool.responses.CarPoolerResponse;
import com.clever.carpool.services.CarPoolerService;
import com.clever.carpool.to.CarPoolerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carpool")
public class CarpoolerController {

    @Autowired
    CarPoolerRepository poolerRepository;
    @Autowired
    CarPoolerService carPoolerService;

    @GetMapping(path = "/{id}")
    public String GetCarPooler(@PathVariable String id){
        return "i'm a carpooler with the id :" + id;
    }

    @PostMapping
    public CarPoolerResponse CreateCarPooler(@RequestBody CarPoolerRequest poolerRequest){

        // PRESENTATION LAYER
        CarPoolerDto poolerDto = new CarPoolerDto();
        BeanUtils.copyProperties(poolerRequest, poolerDto);


        CarPoolerDto createPooler = carPoolerService.createPooler(poolerDto);
        CarPoolerResponse poolerResponse = new CarPoolerResponse();

        BeanUtils.copyProperties(createPooler, poolerResponse);
        return poolerResponse;
    }

    @PutMapping
    public String UpdateCarPooler(){
        return "Updated";
    }

    @DeleteMapping
    public String DeleteCarPooler(){
        return "Deleted";
    }


}
