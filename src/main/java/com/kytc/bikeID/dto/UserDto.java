package com.kytc.bikeID.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;


@Data
public class UserDto {
    @Id
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
}
