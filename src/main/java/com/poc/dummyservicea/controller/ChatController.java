package com.poc.dummyservicea.controller;

import com.poc.dummyservicea.model.Message;
import com.poc.dummyservicea.model.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@EnableBinding(StreamChannel.class)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {

    @Autowired
    private StreamChannel streamChannel;

    @MessageMapping("/chat")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());

        OutputMessage msg = new OutputMessage(message.getFrom(), message.getText(), time);

        log.error("NOOOOOOOOOOOOOO");

        streamChannel.socketOutputStream().send(
                MessageBuilder.withPayload(message.getFrom() + "/" + message.getText()).build()
        );

        return msg;
    }
}
