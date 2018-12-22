package com.etiya.project.controller;


import com.etiya.project.domain.User;
import com.etiya.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Furkan
 * Controller
 */



@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.DELETE,RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        User user = userService.findById(Long.valueOf(id));

        return ResponseEntity.ok().body(user);


    }

    @GetMapping("user/all")
    @ResponseBody
    public ResponseEntity<Set<User>> getUsers() {

        Set<User> userSet = userService.findAll();

        return ResponseEntity.ok().body(userSet);


    }

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable String id) {

        log.debug("deleting by id: " + id);

        userService.deleteById(Long.valueOf(id));

        return ResponseEntity.ok().body(true);


    }


    @PostMapping("user")
    public ResponseEntity<Boolean> newUser(@Valid @RequestBody User newUser) {
        userService.save(newUser);
        return ResponseEntity.ok().body(true);
    }


}
