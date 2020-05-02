package com.cms.utils;

import com.cms.dto.RegisterUserDto;
import com.cms.dto.UserDto;
import com.cms.model.User;

public class UserConverter {

    public static User registerUserToUser(RegisterUserDto registerUserDto) {
        return new User(registerUserDto.getUsername(), registerUserDto.getPassword(), registerUserDto.getFullName(),
                registerUserDto.getEmail(), registerUserDto.getAffiliation(), registerUserDto.getWebpage());
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto().username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .affiliation(user.getAffiliation())
                .webpage(user.getWebpage());
    }

}
