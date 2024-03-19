package com.example.parkingLot.controller;

import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;


    @GetMapping()
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehicleById(@PathVariable (value = "id") Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
    }
}
