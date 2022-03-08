package com.tms.service;

import com.tms.model.City;
import com.tms.model.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public City getByName(String name) {
        return cityRepository.findById(name).get();
    }

    public void delete(String name) {
        cityRepository.deleteById(name);
    }

}
