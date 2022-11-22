package com.kytc.bikeID.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.dto.UserDto;
import com.kytc.bikeID.exeption.GlobalControllerAdvice;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.service.UserService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    private MockMvc mvc;

    private UserDto dto;

    private List<UserDto> dtoList;

    @Mock
    private UserService service;

    @InjectMocks
    UserController userController;


    @BeforeEach
    public void setup() {

        dto = DtoBuilder.buildUserDto();
        dtoList = DtoBuilder.buildUserListDto();

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalControllerAdvice()).build();
    }


    @Test
    public void getUserTest() throws Exception {

        int id = 2;
        UserDto dto = DtoBuilder.buildUserDto();
        when(service.getUserById(id)).thenReturn(dto);

        mvc.perform(get("/user?id=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(dto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is(dto.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is(dto.getEmail())));


    }

    @Test
    public void getUserWhenDoesNotExist() throws Exception {

        int id = 2;
        given(service.getUserById(id)).willThrow(new NoSuchElementException("Can't find user by id: " + id));

        mvc.perform(get("/user?id=2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void addUserTest() throws Exception {

        when(service.addUser(dto)).thenReturn(dto.getId());
        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(dto.getId()));

    }

    @Test
    public void addUserWhenDoesNotExist() throws Exception {

        given(service.addUser(dto)).willThrow(new ValidationException("User with email " + dto.getEmail() + " already exist"));
        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


    @Test
    public void UpdateUserTest() throws Exception {

        when(service.updateUserById(dto)).thenReturn(dto);
        mvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(dto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is(dto.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is(dto.getEmail())));
    }

    @Test
    public void UpdateUserWhenDoesNotExist() throws Exception {

        given(service.updateUserById(dto)).willThrow(new ValidationException("User id can't be null"));
        mvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void AllUsersListTest() throws Exception {

        when(service.allUsersList()).thenReturn(dtoList);
        UserDto dto1 = dtoList.get(0);

        mvc.perform(get("/user/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dtoList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Is.is(dto1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].password", Is.is(dto1.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", Is.is(dto1.getEmail())));
    }

    @Test
    public void deleteUserTest() throws Exception {

        mvc.perform(delete("/user?id=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));

    }

    @Test
    public void setToAdminTest() throws Exception {

        mvc.perform(put("/user/admin?id=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));

    }

    @Test
    public void setToAdminWhenDoesNotExist() throws Exception {

        given(service.setAdmin(dto.getId())).willThrow(new ValidationException("User with id is not enable"));
        mvc.perform(put("/user/admin?id=" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))).
                andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void checkExpirationCodeTest() throws Exception {

        String code = "0000";
        when(service.checkExpirationCode(dto.getEmail(), code)).thenReturn(true);
        mvc.perform(get("/user/check?code=" + code + "&email=" + dto.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));

    }

    @Test
    public void checkExpirationCodeWhenDoesNotExist() throws Exception {

        String code = "0000";
        given(service.checkExpirationCode(dto.getEmail(), code))
                .willThrow(new ValidationException("Can't find user code by email " + dto.getEmail()));
        mvc.perform(get("/user/check?code=" + code + "&email=" + dto.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))).
                andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    protected static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
