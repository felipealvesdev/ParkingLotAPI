package com.example.parkingLot.vehicle;

import com.example.parkingLot.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OwnerService ownerService;

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
