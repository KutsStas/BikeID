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

        if (isNull(dto.getId())) {
            throw new ValidationException("Can't add technical passport without id");
        }
        Bike bike = bikeRepository.findById(dto.getBikeId())
                .orElseThrow(() -> new NoSuchElementException("Can't find bike by ID " + dto.getBikeId()));
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
            throw new ValidationException("Can't update technical passport without id");
        }
        technicalPassportRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find Technical Passport by id: " + dto.getId()));
        TechnicalPassport technicalPassport = technicalPassportMapper.toTechnicalPassport(dto);
        technicalPassportRepository.save(technicalPassport);
        return dto;
    }

    @Override
    public List<TechnicalPassportDto> allBikesTechnicalPassport(Integer userId) {
        List<TechnicalPassport> technicalPassports = technicalPassportRepository.findAllByBikeId(userId);
        return technicalPassports.stream()
                .map(technicalPassportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTechnicalPassportById(Integer id) {

        technicalPassportRepository.deleteById(id);

    }

}
