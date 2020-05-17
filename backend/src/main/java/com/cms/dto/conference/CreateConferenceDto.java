package com.cms.dto.conference;

import java.util.List;

public class CreateConferenceDto extends ConferenceDto {
    private List<UserRoleDto> users;

    public List<UserRoleDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserRoleDto> users) {
        this.users = users;
    }
}
