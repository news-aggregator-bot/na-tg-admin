package bepicky.bot.admin.message;

import bepicky.bot.core.cmd.ChatCommand;
import bepicky.bot.core.message.handler.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class RegisterMeMessageHandler implements MessageHandler {

    @Override
    public BotApiMethod<Message> handle(ChatCommand cc) {
        return null;
    }

    @Override
    public String trigger() {
        return "/register_me";
    }
}
