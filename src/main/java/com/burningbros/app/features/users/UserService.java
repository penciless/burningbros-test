package com.burningbros.app.features.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private boolean isValidString(String str) {
        return StringUtils.hasLength(str);
    }

    private boolean isValidUserInput(User user) {
        return isValidString(user.getUsername()) && isValidString(user.getPassword());
    }

    public User createUser(User user) {
        if (!isValidUserInput(user)) throw new RuntimeException("Require username and password");

        if (userRepository.findByUsername(user.getUsername()) != null) throw new RuntimeException("User already exists");
        
        String encodedPwd = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPwd);
        user.setRoles(UserRole.USER.name());
        userRepository.save(user);
        return user;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        return new User();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
