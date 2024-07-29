package com.burningbros.app.base;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.burningbros.app.features.users.User;
import com.burningbros.app.features.users.UserService;

@Component
public class BaseController {

    @Autowired
    protected UserService userService;

    protected boolean isAdmin() {
        return userService.getCurrentUser().isAdmin();
    }

    protected boolean isValidUser(Long userId) {
        User user = userService.getCurrentUser();
        return user.isAdmin() || userId != null ? Objects.equals(user.getId(), userId) : false; // Permit admin or owner
    }
}
