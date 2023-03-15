package com.clever.carpool.entities;

import com.clever.carpool.entities.constants.PoolerGender;
import com.clever.carpool.entities.constants.PoolerRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "carpoolers")
@NoArgsConstructor
@AllArgsConstructor
public class CarPoolerEntity implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = -8133308019154591688L;

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private PoolerRole role;

    @Column(nullable = false)
    private String poolerId;

    @Column(name="first_name", nullable=false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable=false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private PoolerGender gender;


    @Column(name="email", nullable=false, length = 120, unique = true)
    private String email;

    @Column(nullable = false)
    private String encryptedPass;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return encryptedPass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
