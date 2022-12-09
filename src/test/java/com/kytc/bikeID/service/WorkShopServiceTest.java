package com.kytc.bikeID.service;

import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.EntityBuilder;
import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import com.kytc.bikeID.exception.ValidationException;
import com.kytc.bikeID.mapper.WorkshopMapper;
import com.kytc.bikeID.repository.UserRepository;
import com.kytc.bikeID.repository.WorkshopRepository;
import com.kytc.bikeID.service.impl.WorkshopServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WorkShopServiceTest {

    @InjectMocks
    WorkshopServiceImpl workshopService;

    @Mock
    UserRepository userRepository;

    @Mock
    WorkshopMapper workshopMapper;

    @Mock
    WorkshopRepository workshopRepository;

    @Test
    public void addWorkshopTest() {

        User user = EntityBuilder.buildUser();
        WorkshopDto dto = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();
        dto.setManagerId(user.getId());
        when(userRepository.findById(dto.getManagerId())).thenReturn(Optional.of(user));
        when(workshopMapper.toWorkshop(dto)).thenReturn(workshop);
        int id = workshopService.addWorkshop(dto);
        assertEquals(workshop.getId(), id);
        verify(workshopRepository, times(1)).save(workshop);
    }

    @Test
    public void addWorkshopWhenManagerIdIsWrong() {

        User user = EntityBuilder.buildUser();
        WorkshopDto dto = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();
        dto.setManagerId(user.getId());

        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> workshopService.addWorkshop(dto));
        assertEquals(thrown.getMessage(), ("Can't find User by ID " + dto.getManagerId()));
        verify(workshopRepository, never()).save(workshop);
    }

    @Test
    public void addWorkshopWhenManagerIdIsNull() {

        WorkshopDto dto = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();

        ValidationException thrown = assertThrows(ValidationException.class, () -> workshopService.addWorkshop(dto));
        assertEquals(thrown.getMessage(), ("Manager id can't be null"));
        verify(workshopRepository, never()).save(workshop);
    }


    @Test
    public void getWorkshopByIdTest() {

        Workshop workshop = EntityBuilder.buildWorkshop();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        when(workshopRepository.findById(workshop.getId())).thenReturn(Optional.of(workshop));
        when(workshopMapper.toDto(workshop)).thenReturn(expected);
        WorkshopDto actual = workshopService.getWorkshopById(workshop.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getWorkshopName(), actual.getWorkshopName());

    }

    @Test
    public void getWorkshopWhenIdIsWrong() {

        Workshop workshop = EntityBuilder.buildWorkshop();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        when(workshopMapper.toDto(workshop)).thenReturn(expected);
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> workshopService.getWorkshopById(workshop.getId()));
        assertEquals(thrown.getMessage(), ("Can't find Workshop by id" + workshop.getId()));
        verify(workshopMapper, never()).toDto(workshop);

    }

    @Test
    public void updateWorkshopTest() {

        User user = EntityBuilder.buildUser();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();
        expected.setManagerId(user.getId());
        when(workshopRepository.findById(expected.getId())).thenReturn(Optional.of(workshop));
        when(userRepository.findById(expected.getManagerId())).thenReturn(Optional.of(user));
        expected.setWorkshopName("WorkshopName Test");
        expected.setAddress("Address Test");
        when(workshopMapper.toWorkshop(expected)).thenReturn(workshop);
        WorkshopDto actual = workshopService.updateWorkshopInfo(expected);
        assertEquals(expected.getWorkshopName(), actual.getWorkshopName());
        assertEquals(expected.getAddress(), actual.getAddress());
        verify(workshopRepository, times(1)).save(workshop);
    }
    @Test
    public void updateWorkshopWhenManagerIdIsWrong() {

        User user = EntityBuilder.buildUser();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();
        expected.setManagerId(user.getId());
        when(workshopRepository.findById(expected.getId())).thenReturn(Optional.of(workshop));
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> workshopService.updateWorkshopInfo(expected));
        assertEquals(thrown.getMessage(), ("Can't find User by ID " + expected.getManagerId()));
        verify(workshopRepository, never()).save(workshop);
    }
    @Test
    public void updateWorkshopWhenIdIsWrong() {

        User user = EntityBuilder.buildUser();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        Workshop workshop = EntityBuilder.buildWorkshop();
        expected.setManagerId(user.getId());
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> workshopService.updateWorkshopInfo(expected));
        assertEquals(thrown.getMessage(), ("Can't find Workshop by id: " + expected.getId()));
        verify(workshopRepository, never()).save(workshop);
    }
    @Test
    public void updateWorkshopWhenIdIsNull() {
        Workshop workshop = EntityBuilder.buildWorkshop();
        WorkshopDto expected = DtoBuilder.buildWorkshopDto();
        expected.setId(null);
        ValidationException thrown = assertThrows(ValidationException.class, () -> workshopService.updateWorkshopInfo(expected));
        assertEquals(thrown.getMessage(), ("Workshop id can't be null"));
        verify(workshopRepository, never()).save(workshop);
    }
    @Test
    public void allWorkshopListTest() {

        List<Workshop> workshops = EntityBuilder.buildWorkshopList();
        List<WorkshopDto> dtoList = DtoBuilder.buildWorkshopDtoList();
        when(workshopRepository.findAll()).thenReturn(workshops);
        when(workshopMapper.toDtoList(workshops)).thenReturn(dtoList);
        List<WorkshopDto> resultList = workshopService.allWorkshopList();
        assertEquals(workshops.size(),resultList.size());
        verify(workshopRepository,times(1)).findAll();
    }

    @Test
    public void deleteWorkshopByIdTest() {
        Workshop workshop = EntityBuilder.buildWorkshop();
        workshopService.deleteById(workshop.getId());
        verify(workshopRepository,times(1)).deleteById(workshop.getId());
    }

}
