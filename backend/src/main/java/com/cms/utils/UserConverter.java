package com.cms.utils;

import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.model.User;

import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

public class UserConverter {

    public static User registerUserToUser(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setAffiliation(registerUserDto.getAffiliation());
        user.setEmail(registerUserDto.getEmail());
        user.setFullName(registerUserDto.getFullName());
        user.setPassword(registerUserDto.getPassword());
        user.setUsername(registerUserDto.getUsername());
        user.setWebpage(registerUserDto.getWebpage());
        return user;
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto()
                .id(user.getUserId().toString())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .affiliation(user.getAffiliation())
                .webpage(user.getWebpage());
    }

    public static List<UserDto> userToUserDto(List<User> users) {
        if(Objects.isNull(users))
            return List.of();
        return users.stream().map(UserConverter::userToUserDto).collect(Collectors.toList());
    }

    public static User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getId());
        user.setWebpage(userDto.getWebpage());
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setAffiliation(userDto.getAffiliation());
        //No password convert, since the userDto has no knowledge about it
        return user;
    }

    public static List<User> userDtoToUser(List<UserDto> users) {
        if(Objects.isNull(users))
            return List.of();
        return users.stream().map(UserConverter::userDtoToUser).collect(Collectors.toList());
    }
}
