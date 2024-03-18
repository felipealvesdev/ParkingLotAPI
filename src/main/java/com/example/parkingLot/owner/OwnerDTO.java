package com.example.parkingLot.owner;

import com.example.parkingLot.vehicle.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OwnerDTO(@NotBlank String name, @NotNull Integer age, List<Vehicle> vehicleList) {
}
