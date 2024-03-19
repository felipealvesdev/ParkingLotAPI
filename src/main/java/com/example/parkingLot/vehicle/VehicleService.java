package com.example.parkingLot.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
