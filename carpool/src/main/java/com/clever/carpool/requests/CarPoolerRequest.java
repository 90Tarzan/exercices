package com.clever.carpool.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarPoolerRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
}
