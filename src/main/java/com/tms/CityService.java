package com.tms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository repo;

    public List<City> listAll() {
        return repo.findAll();
    }

    public void save(City city) {
        repo.save(city);
    }

    public City get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
