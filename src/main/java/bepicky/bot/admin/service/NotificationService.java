package bepicky.bot.admin.service;

import bepicky.bot.core.BotRouter;
import bepicky.bot.core.message.builder.SendMessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class NotificationService implements INotificationService {

    @Autowired
    private BotRouter bot;

    @Override
    public void message(Long chatId, String message) {
        SendMessage msg = new SendMessageBuilder(chatId, message)
            .disableNotification()
            .disableWebPreview()
            .build();
        try {
            bot.execute(msg);
        } catch (TelegramApiException e) {
            log.error("message:notify:failed", e);
        }
    }
}
