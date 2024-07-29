package com.burningbros.app.features.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("leona");
        user.setPassword("password");
        user.setRoles("USER");

        userRepository.save(user);

        User foundUser = userRepository.findByUsername("leona");
        assertNotNull(foundUser);
        assertEquals("leona", foundUser.getUsername());
    }
}

