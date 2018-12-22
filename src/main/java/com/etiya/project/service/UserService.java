package com.etiya.project.service;

import com.etiya.project.domain.User;

public interface UserService extends CrudService<User,Long> {

    User findByLastName(String lastName);


}
