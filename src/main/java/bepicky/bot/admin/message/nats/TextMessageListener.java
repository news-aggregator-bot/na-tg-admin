package bepicky.bot.admin.message.nats;

import bepicky.bot.admin.service.INotificationService;
import bepicky.common.msg.admin.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Slf4j
public class TextMessageListener {

    @Autowired
    private Connection natsConnection;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private INotificationService notificationService;

    @Value("${topics.message.text}")
    private String message;

    @PostConstruct
    public void createDispatcher() {
        Dispatcher dispatcher = natsConnection.createDispatcher(msg -> {
            try {
                TextMessage r = om.readValue(msg.getData(), TextMessage.class);
                handle(r);
            } catch (IOException e) {
                log.error("newsnote:notificaton:failed " + e.getMessage());
            }
        });
        dispatcher.subscribe(message);
    }

    public void handle(TextMessage m) {
        notificationService.message(m.getChatId(), m.getText());
    }

}
