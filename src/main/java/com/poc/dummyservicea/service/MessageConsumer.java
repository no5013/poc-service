package com.poc.dummyservicea.service;

import com.poc.dummyservicea.model.OutputMessage;
import com.poc.dummyservicea.controller.StreamChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class MessageConsumer {

    @Value("${stomp.topic}")
    private String stompTopic;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @KafkaListener(topics = "${kafka.output.topic}", groupId = "${spring.kafka.consumer.group-id}")
////    @SendTo("/topic/messages")
//    public void consumeMessage(String msg) {
//        log.error("Message received: " + msg);
//
//        String[] message = msg.split("/");
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//
//        OutputMessage outputMessage = new OutputMessage(message[0], message[1], time);
//
//        messagingTemplate.convertAndSend(stompTopic, outputMessage);
//
////        return outputMessage;
//    }

    @StreamListener(StreamChannel.SOCKET_INPUT)
    public void consumeMessageStream(@Payload String msg) {
        log.error("Message received: " + msg);

        String[] message = msg.split("/");
        String time = new SimpleDateFormat("HH:mm").format(new Date());

        OutputMessage outputMessage = new OutputMessage(message[0], message[1], time);

        messagingTemplate.convertAndSend(stompTopic, outputMessage);
    }

}
