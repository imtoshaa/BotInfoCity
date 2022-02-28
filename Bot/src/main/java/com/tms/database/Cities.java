package com.tms.database;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Cities {
    MINSK("Минск", "Если вам от 18 до 30 лет, то загляните на Зыбицкую, ваша печень выдержит. " +
            "Не советую заходить в шабаны, говорят там опасненько по вечерам"),
    ZHODINO("Жодино", "Если вы поедете в Жодино, то можете зайти в новенький KFC, его открыли совсем недавно, " +
            "или посмотреть аллею фонарей. Не советую заходить вечером в местный клуб Чили :)"),
    MOSCOW("Москва", "В Москве не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить"),
    NEWYORK("Нью-Йорк", "В Нью-Йорке можно посетить статую свободу," +
            " а если вы приехали отдохнуть от русскоговорящих людей, то не заходите на Брайтон-Бич");

    public static final List<List<InlineKeyboardButton>> BUTTONS = new ArrayList<>();

    static {
        BUTTONS.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text(MINSK.getCityName())
                                .callbackData(MINSK.getCityName())
                                .build(),
                        InlineKeyboardButton.builder()
                                .text(ZHODINO.getCityName())
                                .callbackData(ZHODINO.getCityName())
                                .build()));
        BUTTONS.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text(MOSCOW.getCityName())
                                .callbackData(MOSCOW.getCityName())
                                .build(),
                        InlineKeyboardButton.builder()
                                .text(NEWYORK.getCityName())
                                .callbackData(NEWYORK.getCityName())
                                .build()));
    }
    private final String cityName;

    private final String info;

    Cities(String cityName, String info) {
        this.info = info;
        this.cityName = cityName;
    }
}
