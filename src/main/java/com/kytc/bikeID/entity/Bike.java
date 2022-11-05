package com.kytc.bikeID.entity;

import com.kytc.bikeID.entity.enums.LegalStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Passport.
 */
@Entity
@Getter
@Setter
@Table(name = "bikes")
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private LegalStatus legalStatus;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    private TechnicalPassport technicalPassport;

    @ManyToMany
    @JoinTable(name = "bikes_workshop", joinColumns =
    @JoinColumn(name = "bike_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "id"))
    private List<Workshop> workshops = new ArrayList<>();

}



