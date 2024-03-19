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

    @PostMapping()
    public ResponseEntity<Owner> createOwner(@RequestBody OwnerDTO ownerDTO) {
        var ownerModel = new Owner();
        BeanUtils.copyProperties(ownerDTO, ownerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.saveOwner(ownerModel));
    }
}
