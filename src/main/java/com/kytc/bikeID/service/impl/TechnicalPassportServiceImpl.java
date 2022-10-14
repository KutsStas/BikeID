package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.TechnicalPassport;
import com.kytc.bikeID.exeption.ValidationException;
import com.kytc.bikeID.mapper.TechnicalPassportMapper;
import com.kytc.bikeID.repository.BikeRepository;
import com.kytc.bikeID.repository.TechnicalPassportRepository;
import com.kytc.bikeID.service.TechnicalPassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TechnicalPassportServiceImpl implements TechnicalPassportService {

    private final BikeRepository bikeRepository;

    private final TechnicalPassportRepository technicalPassportRepository;

    private final TechnicalPassportMapper technicalPassportMapper;


    @Override
    public Integer addTechnicalPassport(TechnicalPassportDto dto) {

        Bike bike = bikeRepository.findById(dto.getPassportId())
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by ID " + dto.getPassportId()));
        TechnicalPassport technicalPassport = technicalPassportMapper.toTechnicalPassport(dto);
        technicalPassport.setBike(bike);
        technicalPassportRepository.save(technicalPassport);
        return technicalPassport.getId();
    }


    @Override
    public TechnicalPassportDto getTechnicalPassportById(Integer id) {

        TechnicalPassport technicalPassport = technicalPassportRepository.
                findById(id).orElseThrow(() -> new NoSuchElementException("Can't find Technical Passport by id" + id));
        return technicalPassportMapper.toDto(technicalPassport);

    }


    @Override
    public TechnicalPassportDto updateTechnicalPassportInfo(TechnicalPassportDto dto) {

        if (isNull(dto.getId())) {
            throw new ValidationException("id can't be null");
        }
        technicalPassportRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find Technical Passport by id: " + dto.getId()));
        Bike bike = bikeRepository.findById(dto.getPassportId())
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by ID " + dto.getPassportId()));
        TechnicalPassport technicalPassport = technicalPassportMapper.toTechnicalPassport(dto);
        technicalPassport.setBike(bike);
        technicalPassportRepository.save(technicalPassport);
        return dto;
    }

    @Override
    public List<TechnicalPassportDto> allBikesTechnicalPassport(Integer passportId) {

        List<TechnicalPassport> technicalPassports = technicalPassportRepository.findAllByBikeId(passportId);
        return technicalPassports.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTechnicalPassportById(Integer id) {

        technicalPassportRepository.deleteById(id);

    }

    private TechnicalPassportDto mapToDto(TechnicalPassport technicalPassport) {

        return technicalPassportMapper.toDto(technicalPassport);
    }


}
