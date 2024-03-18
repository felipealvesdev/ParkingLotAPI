package com.example.parkingLot.controller;

import com.example.parkingLot.owner.Owner;
import com.example.parkingLot.owner.OwnerDTO;
import com.example.parkingLot.owner.OwnerRespository;
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

    @GetMapping()
    public ResponseEntity<List<Owner>> getOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRespository.findAll());
    }
}
