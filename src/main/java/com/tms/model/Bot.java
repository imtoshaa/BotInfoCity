package com.tms.model;

import com.tms.service.ButtonsBuilder;
import com.tms.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private CityService cityService;

    @Autowired
    private ButtonsBuilder buttonsBuilder;

    @Override
    public String getBotUsername() {
        return "@InformationAboutCitiesBot";
    }

    public String getBotToken() {
        return "5195837401:AAHotbJz7rDMqwe8oH0k33oyhf73OIXdbEg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            try {
                handleCallback(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        String message = "";
        message = cityService.getByName(callbackQuery.getData()).getInfo();
        execute(SendMessage.builder()
                .text(message)
                .chatId(callbackQuery.getMessage().getChatId().toString())
                .build());
    }

    private void handleMessage(Message message) throws TelegramApiException {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntities =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntities.isPresent()) {
                String command = message.getText().substring(commandEntities.get().getOffset(), commandEntities.get().getLength());
                switch (command) {
                    case "/start" -> {
                        execute(SendMessage.builder()
                                .text("""
                                        Вас приветствует бот, рассказывающий о городах!
                                        Чтобы вывести информацию о поддерживаемых командах введите команду /help (ну или тыкните на ссылку)""")
                                .chatId(message.getChatId().toString())
                                .build());
                    }
                    case "/help" -> {
                        execute(SendMessage.builder()
                                .text("""
                                        Бот поддерживает следующие команды:
                                        /choose_a_city ---> выбрать интересующий Вас город.""")
                                .chatId(message.getChatId().toString())
                                .build());
                    }
                    case "/choose_a_city" -> {
                        execute(SendMessage.builder()
                                .text("Выберите интересующий Вас город!")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttonsBuilder.getButtons()).build())
                                .build());
                    }
                }
            }
        }
    }
}
