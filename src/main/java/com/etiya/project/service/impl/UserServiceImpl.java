package com.etiya.project.service.impl;

import com.etiya.project.domain.User;
import com.etiya.project.exception.NotFoundException;
import com.etiya.project.repository.UserRepository;
import com.etiya.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     *
     *
     * @param lastName
     * @return User
     */
    @Override
    public User findByLastName(String lastName) {

        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);

        return userSet
                .stream()
                .filter(user -> user.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }


    /**
     *
     *
     * @return Set<User>
     */
    @Override
    public Set<User> findAll() {
        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);

        return userSet;
    }

    /**
     *
     * @param id
     * @return User
     */
    @Override
    public User findById(Long id) {

        log.debug(" Getting User by id : " + id);


        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException("User Not Found For ID Value: " + id.toString());
        }


        return userOptional.get();


    }

    /**
     *
     * @param object
     * @return User
     */

    @Override
    public User save(User object) {
        log.debug("Saving User...");


        User savedUser = userRepository.save(object);


        return savedUser;
    }

    /**
     *
     * @param object
     */
    @Override
    public void delete(User object) {
        log.debug("deleting object");
        userRepository.delete(object);
    }

    /**
     *
     * @param id
     */

    @Override
    public void deleteById(Long id) {

        userRepository.deleteById(id);


    }
}
