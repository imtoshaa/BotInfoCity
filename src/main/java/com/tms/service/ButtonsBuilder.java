package com.tms.service;

import com.tms.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ButtonsBuilder {

    @Autowired
    private CityService cityService;

    public List<List<InlineKeyboardButton>> getButtons() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (City city : cityService.findAll()) {
            buttons.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(city.getName())
                                    .callbackData(city.getName())
                                    .build()));
        }
        return buttons;
    }

}
