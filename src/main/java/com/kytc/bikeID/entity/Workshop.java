package com.kytc.bikeID.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Workshop.
 */

@Setter
@Getter
@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String workshopName;

    private String address;

    private String website;

    private Integer workshopPhoneNumber;

    private LocalDate salesDate;

    private Integer workshopRating;

    private String workshopReviews;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User manager;

    @ManyToMany
    @JoinTable(name = "technical_passport_workshop", joinColumns =
    @JoinColumn(name = "workshop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "technical_passport_id", referencedColumnName = "id"))
    private List<TechnicalPassport> technicalPassports;

    @ManyToMany
    @JoinTable(name = "bikes_workshop", joinColumns =
    @JoinColumn(name = "workshop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bike_id", referencedColumnName = "id"))
    private List<Bike> bikes = new ArrayList<>();

}
