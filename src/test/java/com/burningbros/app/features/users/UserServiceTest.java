package com.burningbros.app.features.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setUsername("leona");
        user.setPassword("password");
        user.setRoles("ADMIN");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("USER", createdUser.getRoles()); // Always be USER by default
        assertNotEquals("password", createdUser.getPassword()); // Password get encrypted
    }

    @Test
    public void testCreateUser_Failure() {
        User user = new User();
        user.setId(1L);
        user.setUsername("");
        user.setPassword("password");
        user.setRoles("ADMIN");

        try {
            userService.createUser(user);
        } catch (Exception e) {
            assertEquals(true, e instanceof RuntimeException);
        }
    }
}
