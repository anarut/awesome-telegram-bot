package com.anarut.demobot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Initiate shopping");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("option 1.1" );
        button1.setCallbackData("1.1");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("option 1.2");
        button2.setCallbackData("1.2");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("option 1.3");
        button3.setCallbackData("1.3");

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(List.of(List.of(button1, button2), List.of(button3)));

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat.getId().toString());
        sendMessage.setText("Choose one:");
        sendMessage.setReplyMarkup(keyboard);



        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
