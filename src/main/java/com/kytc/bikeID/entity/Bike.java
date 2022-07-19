package com.kytc.bikeID.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Passport.
 */
@Entity
@Getter
@Setter
@Table(name = "passports")
public class Bike {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bikeType;
    private String bikeBrand;
    private String bikeModel;
    private String bikeColor;
    private Integer frameSize;
    private Integer wheelSize;
    private String frameNumber;
    private Integer warranty;
    private Boolean legalStatus;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "bike")
    private List<TechnicalPassport> technicalPassports;
    @ManyToOne()
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop;

}



