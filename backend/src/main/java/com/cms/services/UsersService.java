package com.cms.services;

import com.cms.dto.conference.UserRoleDto;
import com.cms.dto.user.UserDto;
import com.cms.model.Invitation;
import com.cms.model.User;
import com.cms.repositories.InvitationJpaRepository;
import com.cms.repositories.UserJpaRepository;
import com.cms.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UserJpaRepository userRepository;

    @Autowired
    private InvitationJpaRepository invitationJpaRepository;

    @Value("${predefined.invitation.base}")
    private String INVITATION_URL;

    public List<UserDto> getUsers() {
        return UserConverter.userToUserDto(userRepository.findAll());
    }

    public UserDto getUserDto(String username) {
        return UserConverter.userToUserDto(getUser(username));
    }

    public User getUser(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> sendInvitationIfNeeded(List<UserRoleDto> users) {
        List<String> emails = users.stream().map(UserRoleDto::getEmail).collect(Collectors.toList());
        List<String> existingEmails = userRepository.getAllByEmailIn(emails).stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        users.stream()
                .filter(userRoleDto -> !existingEmails.contains(userRoleDto.getEmail()))
                .forEach(this::createAndInviteUser);

        return userRepository.getAllByEmailIn(emails);
    }

    public User createAndInviteUser(UserRoleDto userRoleDto) {
        User basicUser = new User();
        basicUser.setEmail(userRoleDto.getEmail());
        basicUser.setAffiliation(userRoleDto.getAffiliation());
        basicUser.setFullName(userRoleDto.getFullName());

        User save = userRepository.save(basicUser);
        Invitation createInvitation = new Invitation();
        createInvitation.setUser(save);
        Invitation invitation = invitationJpaRepository.save(createInvitation);
        sendInvitationEmail(userRoleDto.getEmail(), invitation.getInvitationId());
        return invitation.getUser();

    }

    public void sendInvitationEmail(String email, String invitationId) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        try {
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("olah.istvan.dev@gmail.com", "devdevdev");
                }

            });
            session.setDebug(true);

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("olah.istvan.dev@gmail.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("Invitation");

            // Now set the actual message
            String link = INVITATION_URL + "/" + invitationId;
            message.setText("You've been invited to use our application! Please click the following link to register:\n" + link);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

}
