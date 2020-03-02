package com.poc.dummyservicea.repository;

import com.poc.dummyservicea.model.ISprintUser;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    public List<ISprintUser> users = Arrays.asList(
        new ISprintUser("a763bdfc-8592-4c00-9d0d-4bb978a76a53", "user"),
        new ISprintUser("298b6176-75ce-4211-902f-3a8ed180f4f7", "user1"),
        new ISprintUser("ae9265e9-3942-4b8e-a383-9a241a1eb328", "user2")
    );

    public ISprintUser getUserByUsername(String username){
        for(ISprintUser user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
}
