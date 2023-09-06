package com.anarut.demobot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig {

    @Value("${spring.telegram.bot.name}")
    private String name;

    @Value("${spring.telegram.bot.token}")
    private String token;
}
