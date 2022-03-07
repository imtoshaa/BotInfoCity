package com.tms.utils;

import com.tms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class InitiateUtils implements CommandLineRunner {
    //это просто чтобы ручками в таблицу не записывать оставил
    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
//        City minsk = new City();
//        minsk.setName("Минск");
//        minsk.setInfo("Если вам от 18 до 30 лет, то загляните на Зыбицкую, ваша печень выдержит. Не советую заходить в шабаны, говорят там опасненько по вечерам.");
//        cityService.save(minsk);
//
//        City zhodino = new City();
//        zhodino.setName("Жодино");
//        zhodino.setInfo("Если вы поедете в Жодино, то можете зайти в новенький KFC, его открыли совсем недавно, или посмотреть аллею фонарей. Не советую заходить вечером в местный клуб Чили :)");
//        cityService.save(zhodino);
//
//        City newyork = new City();
//        newyork.setName("Нью-Йорк");
//        newyork.setInfo("В Нью-Йорке можно посетить статую свободу, а если вы приехали отдохнуть от русскоговорящих людей, то не заходите на Брайтон-Бич.");
//        cityService.save(newyork);
//
//        City moscow = new City();
//        moscow.setName("Москва");
//        moscow.setInfo("В Москве не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить");
//        cityService.save(moscow);
    }


}
