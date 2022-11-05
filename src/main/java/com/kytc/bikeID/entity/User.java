package com.kytc.bikeID.entity;

import com.kytc.bikeID.entity.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "ui_user_email", columnNames = {"email"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String password;

    @Column
    @NotNull
    private boolean enable = false;


    @OneToMany(mappedBy = "user")
    private List<Bike> bikes = new ArrayList<>();

    @OneToMany(mappedBy = "manager")
    private List<Workshop> workshops = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private LocalDate expirationDate = LocalDate.now();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(role);
    }

    @Override
    public boolean isAccountNonExpired() {

        return expirationDate.isBefore(expirationDate.plusMonths(1L));
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

        return enable;
    }

}
