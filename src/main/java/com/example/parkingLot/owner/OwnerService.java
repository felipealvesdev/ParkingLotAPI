package com.example.parkingLot.owner;

import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private VehicleService vehicleService;


    public Owner saveOwner(Owner owner) {
        for(Vehicle vehicle : owner.getVehicleList()) {
            vehicle.setOwner(owner);
            vehicleService.saveVehicle(vehicle);
        }
        return ownerRepository.save(owner);
    }
}
