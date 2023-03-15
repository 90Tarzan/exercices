package com.clever.carpool.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CarPoolerResponse {

    private String poolerId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
}
