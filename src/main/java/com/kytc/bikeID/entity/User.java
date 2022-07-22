package com.kytc.bikeID.entity;

import com.kytc.bikeID.entity.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;

    @OneToMany(mappedBy = "user")
    private List<Bike> bikes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Workshop> workshops = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
