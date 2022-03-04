package com.tms.dao;

import com.tms.entities.CityEntity;
import com.tms.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CityDao {

    public CityEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CityEntity.class, id);
    }

    public List<CityEntity> findAll() {
        return (List<CityEntity>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM city_table a", CityEntity.class).list();
    }
}
