package com.bluesoft.bank.accounts.services;

import com.bluesoft.bank.accounts.entities.City;
import com.bluesoft.bank.accounts.exceptions.EntityNotFoundException;
import com.bluesoft.bank.accounts.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City validateCityExist(String name) {
        return cityRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
    }
    
}
