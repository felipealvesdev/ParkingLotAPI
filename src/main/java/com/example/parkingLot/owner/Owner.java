package com.example.parkingLot.owner;

import com.example.parkingLot.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "owners")
@Table(name = "owners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicleList;
}
