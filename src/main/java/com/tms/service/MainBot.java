package com.tms.service;

import com.tms.database.cities;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tms.database.cities.*;

public class MainBot extends TelegramLongPollingBot {

    @SneakyThrows
    public static void main(String[] args) {
        MainBot bot = new MainBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);

    }

    @Override
    public String getBotUsername() {
        return "@AntonAuBot";
    }

    public String getBotToken() {
        return "5078182335:AAGMmZUNHicZ5XtDAgNpKN05n6YYUhdZE6s";
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            handleCallback(update.getCallbackQuery());

        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
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

    @SneakyThrows
    private void handleMessage(Message message) {
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
                        return;
                    }
                }
            }
        }
    }
}
