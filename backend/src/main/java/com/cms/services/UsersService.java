package com.cms.services;

import com.cms.model.User;
import com.cms.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository = new UsersRepository();


    public List<User> get() {
        return usersRepository.get();
    }
}
