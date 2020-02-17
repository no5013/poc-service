package com.poc.dummyservicea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class LiveService {

    @Value("${secret.code.lives}")
    private int live;

    public int getCurrentLive(){
        return live;
    }
}
