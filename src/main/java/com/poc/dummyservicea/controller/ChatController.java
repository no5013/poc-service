package com.poc.dummyservicea.controller;

import com.poc.dummyservicea.model.Message;
import com.poc.dummyservicea.model.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.input.topic}")
    private String kafkaInputTopic;

    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        log.error("WAK WAK WAK");

        OutputMessage msg = new OutputMessage(message.getFrom(), message.getText(), time);
        kafkaTemplate.send(kafkaInputTopic, message.getFrom() + "/" + message.getText());

        return msg;
    }
}
