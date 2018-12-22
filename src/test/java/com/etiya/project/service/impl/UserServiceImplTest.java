package com.etiya.project.service.impl;

import com.etiya.project.domain.User;
import com.etiya.project.repository.UserRepository;
import com.etiya.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {


    UserService userService;
    @Mock
    UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userRepository);


    }

    @Test
    void findAll() {
        User user = new User();
        Set<User>  userSet =new HashSet<>();

        userSet.add(user);

        when(userRepository.findAll()).thenReturn(userSet);

        Set<User> users = userService.findAll();

        assertEquals(users.size(),1);
        verify(userRepository,times(1)).findAll();
        verify(userRepository,never()).findById(anyLong());



    }

    @Test
    void findById() {

        User user =new User();
        user.setId(1L);

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        User userReturned = userService.findById(1L);

        assertNotNull(userReturned,"Null User Returned");
        verify(userRepository,times(1)).findById(anyLong());
        verify(userRepository,never()).findAll();






    }

    @Test
    void save() {
        User user =  new User();

        user.setId(1L);

        when(userRepository.save(any())).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser,"Null user");
        verify(userRepository,times(1)).save(any());





    }

    @Test
    void delete() {
        User userToDelete =new User();
        userToDelete.setId(1L);


        userRepository.delete(userToDelete);

        verify(userRepository,times(1)).delete(any());


    }

    @Test
    void deleteById() {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        userRepository.deleteById(idToDelete);


        //then
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}