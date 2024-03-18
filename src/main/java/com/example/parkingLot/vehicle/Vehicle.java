package com.example.parkingLot.vehicle;

import com.example.parkingLot.owner.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private Boolean isParked;
    private Integer year;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Transient
    private String ownerName;

    @PostLoad
    private void fillOwnerName() {
        if(owner != null) {
            this.ownerName = owner.getName();
        }
    }
}
