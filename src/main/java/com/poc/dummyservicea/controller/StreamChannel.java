package com.poc.dummyservicea.controller;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface StreamChannel {
    String SOCKET_OUTPUT = "roytutsOutput";
    String SOCKET_INPUT = "roytutsInput";

    @Output(StreamChannel.SOCKET_OUTPUT)
    SubscribableChannel socketOutputStream();

    @Input(StreamChannel.SOCKET_INPUT)
    SubscribableChannel socketInputStream();
}