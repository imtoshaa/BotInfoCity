package com.tms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Optional;

import static com.tms.database.Cities.*;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

//    public static void main(String[] args) throws TelegramApiException {
//        Bot bot = new Bot();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        telegramBotsApi.registerBot(bot);
//    }

    @Override
    public String getBotUsername() {
        return "@AntonAuBot";
    }

    public String getBotToken() {
        return "5078182335:AAGMmZUNHicZ5XtDAgNpKN05n6YYUhdZE6s";
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
        switch (callbackQuery.getData()) {
            case "Минск" -> message = MINSK.getInfo();
            case "Жодино" -> message = ZHODINO.getInfo();
            case "Москва" -> message = MOSCOW.getInfo();
            case "Нью-Йорк" -> message = NEWYORK.getInfo();
        };
        execute(SendMessage.builder()
                .text(message)
                .chatId(callbackQuery.getMessage().getChatId().toString())
                .build());
    }

    private void handleMessage(Message message) throws TelegramApiException {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntities =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst(); //ищем команду в строке
            if (commandEntities.isPresent()) { //если команда существует
                String command = message.getText().substring(commandEntities.get().getOffset(), commandEntities.get().getLength());
                switch (command) { //это если вдруг будем расширять бота и записывать новые команды
                    case "/choose_a_city" -> {
                        execute(SendMessage.builder()
                                .text("Выберите интересующий Вас город!")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(BUTTONS).build())
                                .build());
                    }
                }
            }
        }
    }
}
