package com.tms.service;

import com.tms.entities.CityEntity;

import java.util.List;

public interface CitiesService {

    void save(CityEntity cityEntity);
    List<CityEntity> getAll();
    CityEntity getCity(String id);
}
