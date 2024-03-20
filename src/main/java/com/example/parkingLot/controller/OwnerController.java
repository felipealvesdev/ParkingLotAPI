package com.example.parkingLot.controller;

import com.example.parkingLot.owner.Owner;
import com.example.parkingLot.owner.OwnerDTO;
import com.example.parkingLot.owner.OwnerRepository;
import com.example.parkingLot.owner.OwnerService;
import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private OwnerService ownerService;

    @GetMapping()
    public ResponseEntity<List<Owner>> getOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOwnerById(@PathVariable(value = "id") Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if(owner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(owner.get());
    }

    @PostMapping()
    public ResponseEntity<Owner> createOwner(@RequestBody OwnerDTO ownerDTO) {
        var owner = new Owner();
        BeanUtils.copyProperties(ownerDTO, owner);
        for(Vehicle vehicle : owner.getVehicleList()) {
            vehicle.setOwner(owner);
            vehicleService.saveVehicle(vehicle);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerRepository.save(owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOwnerById(@PathVariable(value = "id") Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if(owner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found.");
        }
        for(Vehicle vehicle : owner.get().getVehicleList()){
            vehicle.setOwner(null);
        }
        ownerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Owner deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOwnerById(@PathVariable(value = "id") Long id,
                                                 @RequestBody OwnerDTO ownerDTO) {
        Optional<Owner> ownerModel = ownerRepository.findById(id);
        if(ownerModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found.");
        }
        var owner = ownerModel.get();
        BeanUtils.copyProperties(ownerDTO, owner);
        for(Vehicle vehicle : owner.getVehicleList()) {
            vehicle.setOwner(owner);
            vehicleService.saveVehicle(vehicle);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.save(owner));
    }
}
