package bepicky.bot.admin.message;

import bepicky.bot.admin.service.IUserService;
import bepicky.bot.core.cmd.ChatCommand;
import bepicky.bot.core.message.builder.SendMessageBuilder;
import bepicky.bot.core.message.handler.MessageHandler;
import bepicky.common.msg.admin.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class RegisterMeMessageHandler implements MessageHandler {
    @Autowired
    private IUserService userService;

    @Override
    public BotApiMethod<Message> handle(ChatCommand cc) {

        User from = cc.getFrom();
        UserRegistration u = new UserRegistration();
        u.setChatId(cc.getChatId());
        u.setUsername(from.getUserName());
        u.setFirstName(from.getFirstName());
        u.setLastName(from.getLastName());
        u.setRoles(cc.getKey());

        try {
            userService.register(u);
            return new SendMessageBuilder(cc.getChatId(), "user registration request is sent").build();
        } catch (IllegalArgumentException e) {
            return new SendMessageBuilder(cc.getChatId(), e.getMessage()).build();
        }
    }

    @Override
    public String trigger() {
        return "/register";
    }
}
