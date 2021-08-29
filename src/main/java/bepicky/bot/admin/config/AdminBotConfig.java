package bepicky.bot.admin.config;

import bepicky.bot.core.BotRouter;
import bepicky.bot.core.message.CallbackMessageHandlerManager;
import bepicky.bot.core.message.MessageHandlerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Configuration
public class AdminBotConfig {

    @Bean
    public BotRouter adminBot() {
        return new BotRouter();
    }

    @PostConstruct
    public void initBots() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            botsApi.registerBot(adminBot());
        } catch (TelegramApiException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Bean
    public MessageHandlerManager messageHandlerManager() {
        return new MessageHandlerManager(Collections.emptyList(), null, null);
    }

    @Bean
    public CallbackMessageHandlerManager callbackMessageHandlerManager() {
        return new CallbackMessageHandlerManager(Collections.emptyList(), Collections.emptyList());
    }
}