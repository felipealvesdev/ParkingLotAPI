package com.example.parkingLot.controller;

import com.example.parkingLot.owner.Owner;
import com.example.parkingLot.owner.OwnerDTO;
import com.example.parkingLot.owner.OwnerRespository;
import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerRespository ownerRespository;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping()
    public ResponseEntity<List<Owner>> getOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRespository.findAll());
    }

    @PostMapping()
    public ResponseEntity<Owner> createOwner(@RequestBody @Valid OwnerDTO ownerDTO) {
        var ownerModel = new Owner();
        BeanUtils.copyProperties(ownerDTO, ownerModel);
        if(ownerModel.getVehicleList() != null) {
            for(Vehicle vehicle : ownerModel.getVehicleList()) {
                vehicleService.saveVehicle(vehicle);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ownerRespository.save(ownerModel));
    }
}
