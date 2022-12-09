package com.kytc.bikeID.service;

import com.kytc.bikeID.DtoBuilder;
import com.kytc.bikeID.EntityBuilder;
import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.TechnicalPassport;
import com.kytc.bikeID.exception.ValidationException;
import com.kytc.bikeID.mapper.TechnicalPassportMapper;
import com.kytc.bikeID.repository.BikeRepository;
import com.kytc.bikeID.repository.TechnicalPassportRepository;
import com.kytc.bikeID.service.impl.TechnicalPassportServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TechnicalPassportServiceTest {

    @InjectMocks
    TechnicalPassportServiceImpl technicalPassportService;

    @Mock
    BikeRepository bikeRepository;

    @Mock
    TechnicalPassportRepository technicalPassportRepository;

    @Mock
    TechnicalPassportMapper technicalPassportMapper;

    @Test
    public void addTechnicalPassportWithoutId() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        dto.setId(null);
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        ValidationException thrown = assertThrows(ValidationException.class, () -> technicalPassportService.addTechnicalPassport(dto));
        assertEquals(thrown.getMessage(), ("Can't add technical passport without id"));
        verify(technicalPassportRepository, never()).save(passport);
    }

    @Test
    public void addTechnicalPassportTest() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        Bike bike = EntityBuilder.buildBike();
        when(bikeRepository.findById(dto.getBikeId())).thenReturn(Optional.of(bike));
        when(technicalPassportMapper.toTechnicalPassport(dto)).thenReturn(passport);
        int actual = technicalPassportService.addTechnicalPassport(dto);
        assertEquals(passport.getId(), actual);
        verify(technicalPassportRepository, (times(1))).save(passport);
    }

    @Test
    public void addTechnicalPassportWhenBikeIdIsWrong() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();

        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> technicalPassportService.addTechnicalPassport(dto));
        assertEquals(thrown.getMessage(), ("Can't find bike by ID " + dto.getBikeId()));
        verify(technicalPassportRepository, never()).save(passport);
    }

    @Test
    public void getTechnicalPassportByIdTest() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        when(technicalPassportRepository.findById(dto.getId())).thenReturn(Optional.of(passport));
        when(technicalPassportMapper.toDto(passport)).thenReturn(dto);
        technicalPassportService.getTechnicalPassportById(dto.getId());
        assertNotEquals(dto.getId(), passport.getId());
        assertNotEquals(dto.getClientName(), passport.getClientName());
        assertNotEquals(dto.getTechnicalStatus(), passport.getTechnicalStatus());
        verify(technicalPassportRepository, times(1)).findById(dto.getId());
        verify(technicalPassportMapper, times(1)).toDto(passport);
    }

    @Test
    public void getTechnicalPassportWhenIdIsWrong() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        when(technicalPassportMapper.toDto(passport)).thenReturn(dto);
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> technicalPassportService.getTechnicalPassportById(dto.getId()));
        assertEquals(thrown.getMessage(), ("Can't find Technical Passport by id" + dto.getId()));
        verify(technicalPassportMapper, never()).toDto(passport);
    }

    @Test
    public void updateTechnicalPassportInfoTest() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        when(technicalPassportRepository.findById(dto.getId())).thenReturn(Optional.of(passport));
        dto.setTechnicalStatus("Expected Technical Status");
        dto.setClientName("Expected Client Name");
        dto.setTechnicalStatus("Expected Technical Status");
        when(technicalPassportMapper.toTechnicalPassport(dto)).thenReturn(passport);
        TechnicalPassportDto actual = technicalPassportService.updateTechnicalPassportInfo(dto);
        assertEquals("Expected Technical Status", actual.getTechnicalStatus());
        assertEquals("Expected Client Name", actual.getClientName());
        assertEquals("Expected Technical Status", actual.getTechnicalStatus());
        verify(technicalPassportRepository, times(1)).save(passport);
    }

    @Test
    public void updateTechnicalPassportInfoWhenIdIsNull() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        dto.setId(null);
        ValidationException thrown = assertThrows(ValidationException.class, () -> technicalPassportService.updateTechnicalPassportInfo(dto));
        assertEquals(thrown.getMessage(), ("Can't update technical passport without id"));
        verify(technicalPassportRepository, never()).save(passport);
    }

    @Test
    public void updateTechnicalPassportInfoWhenIdIsWrong() {

        TechnicalPassportDto dto = DtoBuilder.buildTechnicalPassportDto();
        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        NoSuchElementException thrown = assertThrows(java.util.NoSuchElementException.class, () -> technicalPassportService.updateTechnicalPassportInfo(dto));
        assertEquals(thrown.getMessage(), ("Can't find Technical Passport by id: " + dto.getId()));
        verify(technicalPassportRepository, never()).save(passport);
    }

    @Test
    public void allBikesTechnicalPassport() {

        List<TechnicalPassport> passports = EntityBuilder.buildTechnicalPassportList();
        List<TechnicalPassportDto> dtoList = DtoBuilder.buildTechnicalPassportDtoList();
        Bike bike = EntityBuilder.buildBike();
        when(technicalPassportRepository.findAllByBikeId(bike.getId())).thenReturn(passports);
        when(technicalPassportMapper.toDosList(passports)).thenReturn(dtoList);

        List<TechnicalPassportDto> result = technicalPassportService.allBikesTechnicalPassport(bike.getId());
        assertEquals(passports.size(), result.size());
        verify(technicalPassportRepository, times(1)).findAllByBikeId(bike.getId());
    }

    @Test
    public void deleteTechnicalPassportById() {

        TechnicalPassport passport = EntityBuilder.buildTechnicalPassport();
        technicalPassportService.deleteTechnicalPassportById(passport.getId());

        verify(technicalPassportRepository, times(1)).deleteById(passport.getId());

    }

}