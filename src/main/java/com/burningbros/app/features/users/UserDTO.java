package com.burningbros.app.features.users;

public class UserDTO extends User {
    public UserDTO(Long id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = null;
        this.roles = user.getRoles();
    }
}
