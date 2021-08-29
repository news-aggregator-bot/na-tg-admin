package bepicky.bot.admin.message.nats;

import bepicky.common.msg.admin.UserRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class UserRegistrationMessagePublisher {

    @Autowired
    private Connection connection;

    @Autowired
    private ObjectMapper om;

    @Value("${topics.user.register}")
    private String userRegistrationSubject;

    public void publish(UserRegistration u) {
        try {
            connection.publish(
                userRegistrationSubject,
                om.writeValueAsString(u).getBytes(StandardCharsets.UTF_8)
            );
        } catch (JsonProcessingException e) {
            log.error("admin:registration:failed : {}", u, e);
            throw new IllegalArgumentException(e);
        }
    }
}
