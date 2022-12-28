package com.kytc.bikeID.entity;

import com.kytc.bikeID.entity.enums.WarrantyWork;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TechnicalInfo.
 */

@Getter
@Setter
@Entity
@Table(name = "technical_passports")
public class TechnicalPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private LocalDate visitDate;

    @Enumerated(EnumType.STRING)
    private WarrantyWork warrantyWork;


    @ManyToMany(mappedBy = "technicalPassports")
    private List<Workshop> workshops = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

}

