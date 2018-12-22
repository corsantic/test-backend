package com.etiya.project.controller;

import com.etiya.project.domain.User;
import com.etiya.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    UserService userService;

    UserController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);


        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();

    }

    @Test
    void getUserById() throws Exception {

        User user = new User();

        user.setId(1L);


        when(userService.findById(anyLong())).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());



    }

    @Test
    void getUsers() throws Exception {

        Set<User> userSet = new HashSet<>();
        User user = new User();

        user.setId(1L);
        User user2 = new User();

        user2.setId(2L);

        userSet.add(user);
        userSet.add(user2);

        when(userService.findAll()).thenReturn(userSet);

        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk());

        //verify
        verify(userService,times(1)).findAll();

    }

}