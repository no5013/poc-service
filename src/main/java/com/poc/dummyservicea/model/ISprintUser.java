package com.poc.dummyservicea.model;

import lombok.Data;

@Data
public class ISprintUser {
    private String id;
    private String username;

    public ISprintUser(String id, String username){
        this.id = id;
        this.username = username;
    }
}
