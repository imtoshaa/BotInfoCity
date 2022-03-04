package com.tms.service;

import com.tms.entities.CityEntity;
import com.tms.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServiceIpml implements CitiesService {

    private final CitiesRepository citiesRepository;

    public CitiesServiceIpml(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }
    @Override
    //сохраняем в базу
    public void save(CityEntity cityEntity) {
        citiesRepository.save(cityEntity);
    }

    @Override
    public List<CityEntity> getAll() {
        return citiesRepository.findAll();
    }

    @Override
    public CityEntity getCity(String id) {
        return citiesRepository.findById(id).get();
    }

}
