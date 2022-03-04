package com.tms.service;

import com.tms.entities.CityEntity;
import com.tms.repositories.CitiesRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {

    private final CitiesRepository citiesRepository;

    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public void save(CityEntity cityEntity) {
        citiesRepository.save(cityEntity);
    }

    public List<CityEntity> getAll() {
        return citiesRepository.findAll();
    }

    public void saveAll (List<CityEntity> cities) {
        citiesRepository.saveAll(cities);
    }

    public Optional<CityEntity> getById(Integer id){
        return citiesRepository.findById(id);
    }

    public void delById(Integer id){
        citiesRepository.deleteById(id);
    }

    public Boolean exist(Example<? extends CityEntity> example){
        return citiesRepository.exists(example);
    }

}
