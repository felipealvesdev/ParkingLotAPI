package com.example.parkingLot.controller;

import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRespository vehicleRespository;


    @GetMapping()
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleRespository.findAll());
    }
}
