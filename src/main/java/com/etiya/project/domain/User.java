package com.etiya.project.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person {

    @Builder
    public User(Long id, String firstName, String lastName, Set<Tool> objects) {
        super(id, firstName, lastName);
        this.objects = objects;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Tool> objects = new HashSet<>();


    public User addObject(Tool object) {
        object.setUser(this);
        this.objects.add(object);
        return this;

    }


}
