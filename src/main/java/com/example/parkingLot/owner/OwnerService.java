package com.example.parkingLot.owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
