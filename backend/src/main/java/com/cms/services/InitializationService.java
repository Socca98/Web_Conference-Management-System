package com.cms.services;

import com.cms.dto.token.TokenDto;
import com.cms.dto.token.TokenInformation;
import com.cms.dto.user.LoginUserDto;
import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.dto.user.UserInformationDto;
import com.cms.exception.IssException;
import com.cms.exception.LoginException;
import com.cms.model.Invitation;
import com.cms.model.User;
import com.cms.repositories.InvitationJpaRepository;
import com.cms.repositories.UserJpaRepository;
import com.cms.utils.UserConverter;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static com.cms.services.SecurityService.createJWT;
import static com.cms.services.SecurityService.decodeJWT;

@Service
public class InitializationService {

    @Autowired
    private UserJpaRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private InvitationJpaRepository invitationJpaRepository;

    @Value("${predefined.admin.username}")
    private String adminUsername;



    public UserDto register(RegisterUserDto registerUserDto) {
        User save = userRepository.save(UserConverter.registerUserToUser(registerUserDto));
        User user = userRepository.getOne(save.getUserId());
        return UserConverter.userToUserDto(user);
    }

    public UserDto completeInvitation(String invitationId, RegisterUserDto registerUserDto) {
        Optional<Invitation> invitation = invitationJpaRepository.findById(invitationId);
        if (invitation.isEmpty()) {
            throw new IssException("Invitation doesn't exists!");
        }

        User basicUser = invitation.get().getUser();
        basicUser.setUsername(registerUserDto.getUsername());
        basicUser.setFullName(registerUserDto.getFullName());
        basicUser.setEmail(registerUserDto.getEmail());
        basicUser.setAffiliation(registerUserDto.getAffiliation());
        basicUser.setWebpage(registerUserDto.getWebpage());
        basicUser.setPassword(registerUserDto.getPassword());
        User savedUser = userRepository.save(basicUser);
        return UserConverter.userToUserDto(savedUser);
    }


    public TokenDto login(LoginUserDto loginUser) {
        try {
            Optional<String> userId = userRepository.login(loginUser.getUsername(), loginUser.getPassword());
            if (userId.isPresent()) {
                return new TokenDto()
                        .token(createToken(loginUser.getUsername()))
                        .refreshToken(createRefreshToken(loginUser.getUsername()))
                        .timeout(3600L);
            }
            throw new LoginException("Invalid password!");
        } catch (EntityNotFoundException exception) {
            throw new LoginException("User not fount!");
        }
    }

    public TokenDto refresh(String refreshToken) {
        Jws<Claims> claims = decodeJWT(refreshToken);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwsHeader header = claims.getHeader();
        if (header.get("scope").equals("refresh") && claims.getBody().getExpiration().before(now)) {
            return new TokenDto()
                    .token(createToken((String) header.get("username")))
                    .refreshToken(refreshToken)
                    .timeout(3600L);
        }
        throw new LoginException("Could not refresh token!");
    }

    public String getUsername(String token) {
        return SecurityService.getUsername(token);
    }

    private static String createRefreshToken(String username) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("scope", "refresh");
        return createJWT(UUID.randomUUID().toString(), headers, 28800);
    }

    private static String createToken(String username) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("scope", "auth");
        return createJWT(UUID.randomUUID().toString(), headers, 3600);
    }


    public UserInformationDto getUserInformation(String conferenceId) {
        User user = userRepository.getUserByUsername(securityService.getUsernameFromContext());
        UserInformationDto userInformationDto = new UserInformationDto()
                .username(user.getUsername())
                .affiliation(user.getAffiliation());
        if (Objects.nonNull(conferenceId)) {
            //TODO: populate role if conference not null
        }
        if(user.getUsername().equals(adminUsername)) {
            userInformationDto.setChair(true);
        } else {
            userInformationDto.setChair(false);
        }
        return userInformationDto;
    }

    public TokenInformation getTokenInformation() {
        return securityService.getTokenInformation();
    }
}
