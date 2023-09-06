package com.anarut.demobot;

import com.anarut.demobot.commands.HelloCommand;
import com.anarut.demobot.commands.HelpCommand;
import com.anarut.demobot.commands.StartCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingCommandBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);

    private final String botUsername;

    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getToken());
        this.botUsername = botConfig.getName();

        register(new HelloCommand());
        register(new HelpCommand(this));
        register(new StartCommand());

        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("Unknown command '" + message.getText() + "'. Try /help ");
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                LOGGER.error("Error occurred", e);
            }
        });
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId());
                echoMessage.setText("Your message:\n" + message.getText());

                if (message.getText().equals("btn1")) {
                    KeyboardRow keyboardButtons2 = new KeyboardRow();
                    keyboardButtons2.addAll(List.of("kb1", "kb2", "kb3"));

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setKeyboard(List.of(keyboardButtons2));
                    replyKeyboardMarkup.setOneTimeKeyboard(true);
                    replyKeyboardMarkup.setSelective(true);
                    echoMessage.setReplyMarkup(replyKeyboardMarkup);
                }

                try {
                    execute(echoMessage);
                } catch (TelegramApiException e) {
                    LOGGER.error("Error occurred", e);
                }
            }

            SendChatAction sendChatAction = new SendChatAction();
            sendChatAction.setChatId(message.getChatId());
            sendChatAction.setAction(ActionType.UPLOADPHOTO);

            try {
                execute(sendChatAction);
            } catch (TelegramApiException e) {
                LOGGER.error("Error occurred", e);
            }
        }
    }
}
