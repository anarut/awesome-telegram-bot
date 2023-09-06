package com.anarut.demobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    private final ICommandRegistry commandRegistry;

    public HelpCommand(ICommandRegistry commandRegistry) {
        super("help", "Show all commands");
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        StringBuilder helpMessageBuilder = new StringBuilder("<b>Help</b>\n");
        helpMessageBuilder.append("Registered commands:\n");

        for (IBotCommand botCommand : commandRegistry.getRegisteredCommands()) {
            helpMessageBuilder
                    .append("\n/")
                    .append(botCommand.getCommandIdentifier())
                    .append(" - ")
                    .append(botCommand.getDescription());
        }

        SendMessage helpMessage = new SendMessage();
        helpMessage.setChatId(chat.getId().toString());
        helpMessage.enableHtml(true);
        helpMessage.setText(helpMessageBuilder.toString());

        try {
            absSender.execute(helpMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Error occurred", e);
        }
    }
}
