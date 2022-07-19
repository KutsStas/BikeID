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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
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
    private User user;

    @ManyToMany
    @JoinTable(name = "techpassport_workshop",
            joinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tehcpassport_id", referencedColumnName = "id"))
    private List<TechnicalPassport> technicalPassports;

    @OneToMany(mappedBy = "workshop")
    private List<Bike> bikes;

}
