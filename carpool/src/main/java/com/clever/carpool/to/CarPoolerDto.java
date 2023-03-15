package com.clever.carpool.to;

import com.clever.carpool.entities.constants.PoolerRole;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPoolerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4169819780318354886L;

    private String id;
    private String poolerId;
    private PoolerRole role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPass;
    private String gender;

}
