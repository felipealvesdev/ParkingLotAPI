package com.example.parkingLot.owner;

import com.example.parkingLot.vehicle.Vehicle;

import java.util.List;

public record OwnerDTO(String name, Integer age, List<Vehicle> vehicleList) {
}
