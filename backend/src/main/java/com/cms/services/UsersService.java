package com.cms.services;

import com.cms.model.User;
import com.cms.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserJpaRepository usersRepository;

    public List<User> get() {
        return usersRepository.findAll();
    }

    public User login(User user) {
        return usersRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public void register(User user) {
        usersRepository.save(user);
    }
}
