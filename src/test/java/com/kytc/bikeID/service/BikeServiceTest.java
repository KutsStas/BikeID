package com.kytc.bikeID.service;

import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.EntityBuilder;
import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import com.kytc.bikeID.entity.enums.LegalStatus;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.BikeMapper;
import com.kytc.bikeID.repository.BikeRepository;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.repository.WorkshopRepository;
import com.kytc.bikeID.service.impl.BikeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BikeServiceTest {

    @InjectMocks
    BikeServiceImpl bikeService;

    @Mock
    BikeMapper bikeMapper;

    @Mock
    BikeRepository bikeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    WorkshopRepository workshopRepository;

    @Mock
    SecurityContextHolder securityContextHolder;


    @Test
    public void addBikeTest() {

        Bike bike = EntityBuilder.buildBike();
        BikeDto dto = DtoBuilder.buildBike();
        User user = EntityBuilder.buildUser();
        Workshop workshop = EntityBuilder.buildWorkshop();
        when(workshopRepository.findById(dto.getWorkshopId())).thenReturn(Optional.of(workshop));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(bikeMapper.toEntity(dto)).thenReturn(bike);
        int id = bikeService.addBike(dto);
        assertEquals(id, bike.getId());
        assertEquals(LegalStatus.LEGAL, bike.getLegalStatus());
        assertEquals(user, bike.getUser());
        verify(bikeRepository, times(1)).save(bike);
    }

    @Test
    public void addBikeWithWrongUser() {

        BikeDto expected = DtoBuilder.buildBike();
        expected.setUserId(3);
        Workshop workshop = EntityBuilder.buildWorkshop();
        when(workshopRepository.findById(expected.getWorkshopId())).thenReturn(Optional.of(workshop));
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.addBike(expected));
        assertEquals(thrown.getMessage(), ("Can't find User by ID " + expected.getUserId()));
    }

    @Test
    public void addBikeWithWrongWorkshop() {

        BikeDto expected = DtoBuilder.buildBike();
        expected.setWorkshopId(3);
        ValidationException thrown = assertThrows(ValidationException.class, () -> bikeService.addBike(expected));
        assertEquals(thrown.getMessage(), ("Can't find workshop by Id " + expected.getWorkshopId()));
    }

    @Test
    public void getBikeByIdTest() {

        BikeDto expected = DtoBuilder.buildBike();
        Bike bike = EntityBuilder.buildBike();
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(bike));
        when(bikeMapper.toDto(bike)).thenReturn(expected);
        BikeDto actual = bikeService.getBikeById(expected.getId());
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getBikeBrand(), expected.getBikeBrand());
        assertEquals(actual.getBikeColor(), expected.getBikeColor());
        assertEquals(actual.getBikeModel(), expected.getBikeModel());
        assertEquals(actual.getBikeType(), expected.getBikeType());
    }

    @Test
    public void getBikeByIdWithWrongId() {

        BikeDto expected = DtoBuilder.buildBike();
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.getBikeById(expected.getId()));
        assertEquals(thrown.getMessage(), ("Can't find bike by id: " + expected.getId()));
    }


    @Test
    public void checkBikeLegalStatusTest() {

        Bike expected = EntityBuilder.buildBike();
        expected.setLegalStatus(LegalStatus.ILLEGAL);
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        String actual = bikeService.checkBikeLegalStatus(expected.getId());
        assertEquals(expected.getLegalStatus().toString(), actual);
    }

    @Test
    public void checkBikeLegalStatusWithWrongBikeID() {

        Bike expected = EntityBuilder.buildBike();
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.checkBikeLegalStatus(expected.getId()));
        assertEquals(thrown.getMessage(), ("Can't find bike by id: " + expected.getId()));

    }

    @Test
    public void checkBikeLegalStatusWhenIsNull() {

        Bike expected = EntityBuilder.buildBike();
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        ValidationException thrown = assertThrows(ValidationException.class, () -> bikeService.checkBikeLegalStatus(expected.getId()));
        assertEquals(thrown.getMessage(), ("Can't find legal status bike with id: " + expected.getId()));

    }

    @Test
    public void updateBikeByIdTest() {

        Bike bike = EntityBuilder.buildBike();
        BikeDto expected = DtoBuilder.buildBike();
        User user = EntityBuilder.buildUser();
        Workshop workshop = EntityBuilder.buildWorkshop();
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(bike));
        when(userRepository.findById(expected.getUserId())).thenReturn(Optional.of(user));
        when(workshopRepository.findById(expected.getWorkshopId())).thenReturn(Optional.of(workshop));
        expected.setBikeBrand("Test Brand");
        expected.setBikeColor("Test Color");
        expected.setBikeModel("Test Model");
        expected.setBikeType("Test Type");
        when(bikeMapper.toEntity(expected)).thenReturn(bike);
        BikeDto actual = bikeService.updateBikeById(expected);
        assertEquals("Test Type", actual.getBikeType());
        assertEquals("Test Brand", actual.getBikeBrand());
        assertEquals("Test Color", actual.getBikeColor());
        assertEquals("Test Model", actual.getBikeModel());
        verify(bikeRepository, times(1)).save(bike);

    }

    @Test
    public void updateBikeByIdWhenUserIsWrong() {

        Bike bike = EntityBuilder.buildBike();
        BikeDto expected = DtoBuilder.buildBike();
        Workshop workshop = EntityBuilder.buildWorkshop();
        when(workshopRepository.findById(expected.getWorkshopId())).thenReturn(Optional.of(workshop));
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(bike));
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.updateBikeById(expected));
        assertEquals(thrown.getMessage(), ("Can't find User by ID " + expected.getUserId()));
        verify(bikeRepository, (never())).save(bike);

    }

    @Test
    public void updateBikeByIdWhenWorkshopIsWrong() {

        Bike bike = EntityBuilder.buildBike();
        BikeDto expected = DtoBuilder.buildBike();
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(bike));

        ValidationException thrown = assertThrows(ValidationException.class, () -> bikeService.updateBikeById(expected));
        assertEquals(thrown.getMessage(), ("Can't find workshop by Id " + expected.getWorkshopId()));
        verify(bikeRepository, (never())).save(bike);

    }

    @Test
    public void updateBikeByIdWhenBikeIsWrong() {

        BikeDto expected = DtoBuilder.buildBike();

        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.updateBikeById(expected));
        assertEquals(thrown.getMessage(), ("Can't find bike by id: " + expected.getId()));

    }

    @Test
    public void updateBikeByIdWhenBikeIdIsnull() {

        BikeDto expected = DtoBuilder.buildBike();
        expected.setId(null);

        ValidationException thrown = assertThrows(ValidationException.class, () -> bikeService.updateBikeById(expected));
        assertEquals(thrown.getMessage(), ("Id can't be null"));
    }

    @Test
    public void listOfStolenBikesTest() {

        List<Bike> bikes = EntityBuilder.buildBikeList();
        List<BikeDto> DtoList = DtoBuilder.buildBikeDtoList();
        when(bikeRepository.findAllByRole()).thenReturn(bikes);
        when(bikeMapper.toDtoList(bikes)).thenReturn(DtoList);
        List<BikeDto> result = bikeService.listOfStolenBikes();
        assertEquals(result.size(), bikes.size());
        verify(bikeRepository, times(1)).findAllByRole();
    }

    @Test
    public void updateBikeLegalStatusTest() {

        Bike expected = EntityBuilder.buildBike();
        BikeDto dto = DtoBuilder.buildBike();
        User user = EntityBuilder.buildUser();
        List<Bike> bikes = EntityBuilder.buildBikeList();
        bikes.add(expected);
        user.setBikes(bikes);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        expected.setLegalStatus(LegalStatus.LEGAL);
        when(bikeMapper.toDto(expected)).thenReturn(dto);
        bikeService.updateBikeLegalStatus(expected.getId());
        assertEquals(expected.getLegalStatus(), LegalStatus.ILLEGAL);
        verify(bikeRepository, times(1)).save(expected);
    }

    @Test
    public void updateBikeLegalStatusTestWhenBikeIdIsWrong() {

        Bike expected = EntityBuilder.buildBike();
        BikeDto dto = DtoBuilder.buildBike();
        User user = EntityBuilder.buildUser();
        List<Bike> bikes = EntityBuilder.buildBikeList();
        bikes.add(expected);
        user.setBikes(bikes);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> bikeService.updateBikeLegalStatus(expected.getId()));
        assertEquals(thrown.getMessage(), ("Can't find bike by id: " + expected.getId()));
        verify(bikeRepository, never()).save(expected);

    }

    @Test
    public void updateBikeLegalStatusWhenBikeIdIsWrongForUser() {

        Bike expected = EntityBuilder.buildBike();
        BikeDto dto = DtoBuilder.buildBike();
        User user = EntityBuilder.buildUser();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        when(bikeRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        expected.setLegalStatus(LegalStatus.LEGAL);
        when(bikeMapper.toDto(expected)).thenReturn(dto);
        ValidationException thrown = assertThrows(ValidationException.class, () -> bikeService.updateBikeLegalStatus(expected.getId()));
        assertEquals(thrown.getMessage(), ("Bike id for this user is wrong " + expected.getId()));
        verify(bikeRepository, never()).save(expected);
    }

    @Test
    public void allUsersBikeTest() {

        List<Bike> bikes = EntityBuilder.buildBikeList();
        List<BikeDto> dtoList = DtoBuilder.buildBikeDtoList();
        User user = EntityBuilder.buildUser();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        when(bikeRepository.findAllByUser(user)).thenReturn(bikes);
        when(bikeMapper.toDtoList(bikes)).thenReturn(dtoList);
        bikeService.allUsersBike();
        assertEquals(bikes.size(), dtoList.size());
        verify(bikeRepository, times(1)).findAllByUser(user);
    }

    @Test
    public void deleteBikeById() {

        Bike bike = EntityBuilder.buildBike();
        when(bikeRepository.findById(bike.getId())).thenReturn(Optional.of(bike));
        bikeService.deleteBikeById(bike.getId());
        verify(bikeRepository, times(1)).deleteById(bike.getId());
    }

}