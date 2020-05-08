package com.cms.services;

import com.cms.dto.user.UserDto;
import com.cms.repositories.UserJpaRepository;
import com.cms.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserJpaRepository usersRepository;

    public List<UserDto> getUsers() {
        return UserConverter.userToUserDto(usersRepository.findAll());
    }


    public UserDto getUser(String username) {
        return UserConverter.userToUserDto(usersRepository.getOne(username));
    }

}
