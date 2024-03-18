package com.example.parkingLot.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRespository vehicleRespository;

    public void saveVehicle(Vehicle vehicle) {
        vehicleRespository.save(vehicle);
    }

}
