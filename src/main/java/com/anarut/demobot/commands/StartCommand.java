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

        ReplyKeyboard keyboard;

        if (false) {
            InlineKeyboardButton button1 = new InlineKeyboardButton();
            button1.setText("btn1");
            button1.setCallbackData("callback data");
            button1.setSwitchInlineQuery("switch inline query");
            button1.setUrl("onliner1.by");
            InlineKeyboardButton button2 = new InlineKeyboardButton();
            button2.setText("btn2");
            button2.setUrl("onliner2.by");
            InlineKeyboardButton button3 = new InlineKeyboardButton();
            button3.setText("btn3");
            button3.setUrl("onliner2.by");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(List.of(List.of(button1, button2),List.of(button3)));
            keyboard = inlineKeyboardMarkup;
        } else {
            KeyboardButton button1 = new KeyboardButton();
            button1.setText("btn1");
            KeyboardButton button2 = new KeyboardButton();
            button2.setText("btn2");
            KeyboardButton button3 = new KeyboardButton();
            button3.setText("btn3");

            KeyboardRow keyboardButtons1 = new KeyboardRow();
            keyboardButtons1.add(button1);
            keyboardButtons1.add(button2);
            keyboardButtons1.add(button3);

            KeyboardRow keyboardButtons2 = new KeyboardRow();
            keyboardButtons2.addAll(List.of("kb1", "kb2", "kb3"));

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(List.of(keyboardButtons1, keyboardButtons2));
            replyKeyboardMarkup.setResizeKeyboard(true);
//            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setSelective(true);

            keyboard = replyKeyboardMarkup;
        }


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
