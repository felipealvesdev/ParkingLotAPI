package com.example.parkingLot.controller;

import com.example.parkingLot.owner.OwnerService;
import com.example.parkingLot.vehicle.Vehicle;
import com.example.parkingLot.vehicle.VehicleDTO;
import com.example.parkingLot.vehicle.VehicleRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private OwnerService ownerService;


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

    @PostMapping()
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        var vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO, vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleRepository.save(vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicleById(@PathVariable( value = "id") Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
        }
        if(vehicle.get().getOwner() != null) {
            vehicle.get().getOwner().getVehicleList().remove(vehicle.get());
        }
        vehicleRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicleById(@PathVariable(value = "id") Long id,
                                                    @RequestBody VehicleDTO vehicleDTO) {
        Optional<Vehicle> vehicleModel = vehicleRepository.findById(id);
        if(vehicleModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
        }
        var vehicle = vehicleModel.get();
        BeanUtils.copyProperties(vehicleDTO, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleRepository.save(vehicle));
    }
}
