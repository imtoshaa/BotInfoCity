package com.tms.utils;

import com.tms.entities.CityEntity;
import com.tms.service.CitiesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitiateUtils implements CommandLineRunner {

    private final CitiesService citiesService;

    public InitiateUtils(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    public void run(String... args) {

        List<CityEntity> cities = new ArrayList<>(
                Arrays.asList(
                        new CityEntity().setCityName("Минск")
                                .setInfo("Если вам от 18 до 30 лет, то загляните на Зыбицкую, ваша печень выдержит. " +
                                        "Не советую заходить в шабаны, говорят там опасненько по вечерам"),
                        new CityEntity().setCityName("Москва")
                                .setInfo("В Москве не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить"),
                        new CityEntity().setCityName("Жодино")
                                .setInfo("Если вы поедете в Жодино, то можете зайти в новенький KFC, его открыли совсем недавно, " +
                                        "или посмотреть аллею фонарей. Не советую заходить вечером в местный клуб Чили :)"),
                        new CityEntity().setCityName("Нью-Йорк")
                                .setInfo("В Нью-Йорке можно посетить статую свободу," +
                                        " а если вы приехали отдохнуть от русскоговорящих людей, то не заходите на Брайтон-Бич")
                )
        );

        citiesService.saveAll(cities);
        System.out.println("\nТаблица городов");
        for (CityEntity cityEntity : cities) {
            System.out.println(cityEntity);
        }

    }
}
