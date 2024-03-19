package com.example.parkingLot.vehicle;

import com.example.parkingLot.owner.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "vehicles")
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private Integer year;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Transient
    private String ownerName;

    @PostLoad
    private void fillOwnerName() {
        if(owner != null) {
            ownerName = owner.getName();
        }
    }
}
