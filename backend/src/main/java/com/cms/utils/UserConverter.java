package com.cms.utils;

import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.model.User;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<UserDto> userToUserDto(List<User> users) {
        return users.stream().map(UserConverter::userToUserDto).collect(Collectors.toList());
    }

}
