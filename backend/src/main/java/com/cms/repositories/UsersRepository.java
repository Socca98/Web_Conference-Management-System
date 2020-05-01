package com.cms.repositories;


import com.cms.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UsersRepository {

    public List<User> get() {
        User u1 = new User(
                "username1",
                "password1",
                "rn1",
                "email1",
                "affiliation1",
                "webpage1"
        );
        User u2 = new User(
                "username2",
                "password2",
                "rn2",
                "email2",
                "affiliation2",
                "webpage2"
        );

        return Arrays.asList(u1, u2);


    }

    public void login(User user) {
        if(user.getPassword().equals("naspa"))
            throw new RuntimeException("Invalid password!");
    }

    public void register(User user) {

    }
}
