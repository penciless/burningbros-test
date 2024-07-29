package com.burningbros.app.features.users;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("leona");
        user.setPassword("password");

        Map<String, String> map = new HashMap<>();
        map.put("username", "leona");
        map.put("password", "password");
        
        String userJson = objectMapper.writeValueAsString(map);

        Mockito.when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/users/register")
                .contentType("application/json")
                .content(userJson)
        )
        .andExpect(status().isCreated());
    }
}

