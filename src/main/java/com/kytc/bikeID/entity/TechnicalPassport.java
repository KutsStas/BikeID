package com.kytc.bikeID.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
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

    private String bikeName;

    private String serviceWork;

    private Boolean warrantyWork;

    private String workShop;

    private String clientName;

    private String servicemenName;

    private String replacementParts;

    private Integer workPrise;

    private String technicalStatus;

    private String serviceMenComments;


    @ManyToMany(mappedBy = "technicalPassports")
    private List<Workshop> workshops;

    @OneToOne
    private Bike bike;

}
