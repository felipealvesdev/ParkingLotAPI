package com.example.parkingLot.owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> findOwnerById(Long id) throws Exception{
        Optional<Owner> ownerModel = ownerRepository.findById(id);
        if(ownerModel.isEmpty()) {
            throw new Exception("Owner not found.");
        }
        return ownerModel;
    }
}
