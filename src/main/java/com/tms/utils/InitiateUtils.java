package com.tms.utils;

import com.tms.entities.CityEntity;
import com.tms.service.CitiesServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class InitiateUtils implements CommandLineRunner {

    private final CitiesServiceIpml citiesService;

    public InitiateUtils(CitiesServiceIpml citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    public void run(String... args) {

        CityEntity minsk = new CityEntity();
        minsk.setCityName("Минск");
        minsk.setInfo("Если вам от 18 до 30 лет, то загляните на Зыбицкую, ваша печень выдержит. " +
                "Не советую заходить в шабаны, говорят там опасненько по вечерам");

        CityEntity moscow = new CityEntity();
        moscow.setCityName("Москва");
        moscow.setInfo("В Москве не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить");

        CityEntity zhodino = new CityEntity();
        zhodino.setCityName("Жодино");
        zhodino.setInfo("Если вы поедете в Жодино, то можете зайти в новенький KFC, его открыли совсем недавно, " +
                "или посмотреть аллею фонарей. Не советую заходить вечером в местный клуб Чили :)");

        CityEntity newyork = new CityEntity();
        newyork.setCityName("Нью-Йорк");
        newyork.setInfo("В Нью-Йорке можно посетить статую свободу," +
                " а если вы приехали отдохнуть от русскоговорящих людей, то не заходите на Брайтон-Бич");

        citiesService.save(minsk);
        citiesService.save(moscow);
        citiesService.save(zhodino);
        citiesService.save(newyork);
        System.out.println(citiesService.getCity("Москва"));

    }
}
