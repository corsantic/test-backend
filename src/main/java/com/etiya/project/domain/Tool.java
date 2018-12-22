package com.etiya.project.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tools")
@Entity
public class Tool extends BaseEntity {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "type_id")
    private ToolType toolType;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tool",fetch = FetchType.EAGER)
    private Set<Maintenance> maintenances = new HashSet<>();


}
