package com.example.parkingLot.vehicle;

import com.example.parkingLot.owner.Owner;

public record VehicleDTO(String brand, String model, Boolean isParked, Integer year, Owner owner) {
}
