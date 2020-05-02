package com.cms.services;

import com.cms.dto.RegisterUserDto;
import com.cms.dto.UserDto;
import com.cms.model.User;
import com.cms.repositories.UserJpaRepository;
import com.cms.repositories.UsersRepository;
import com.cms.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializationService {

    @Autowired
    UserJpaRepository userRepository;

    public UserDto register(RegisterUserDto registerUserDto) {
        userRepository.save(UserConverter.registerUserToUser(registerUserDto));
        User user = userRepository.getOne(registerUserDto.getUsername());
        return UserConverter.userToUserDto(user);
    }
}
