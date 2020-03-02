package com.poc.dummyservicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import th.co.scb.accesshelper.filter.AccessFilter;

@Service
@RefreshScope
public class LiveService {

    @Value("${secret.code.lives}")
    private int live;

    public int getCurrentLive(){
        return live;
    }
}
