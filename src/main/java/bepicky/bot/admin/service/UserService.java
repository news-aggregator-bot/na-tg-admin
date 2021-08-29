package bepicky.bot.admin.service;

import bepicky.bot.admin.message.nats.UserRegistrationMessagePublisher;
import bepicky.common.msg.admin.UserRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRegistrationMessagePublisher registrationMessagePublisher;

    @Override
    public void register(UserRegistration u) {
        log.info("user:registration: {}", u);
        registrationMessagePublisher.publish(u);
    }
}
