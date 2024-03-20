package com.example.parkingLot.vehicle;

import com.example.parkingLot.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OwnerService ownerService;

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findVehicleById(Long id) throws Exception {
        Optional<Vehicle> vehicleModel = vehicleRepository.findById(id);
        if(vehicleModel.isEmpty()) {
            throw new Exception("Vehicle not found.");
        }
        return vehicleModel;
    }
}
