package com.etiya.project.bootstrap;

import com.etiya.project.domain.Maintenance;
import com.etiya.project.domain.Tool;
import com.etiya.project.domain.ToolType;
import com.etiya.project.domain.User;
import com.etiya.project.repository.MaintenanceRepository;
import com.etiya.project.repository.ToolTypeRepository;
import com.etiya.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final ToolTypeRepository toolTypeRepository;
    private final MaintenanceRepository maintenanceRepository;

    public DataLoader(UserRepository userRepository, ToolTypeRepository toolTypeRepository, MaintenanceRepository maintenanceRepository) {
        this.userRepository = userRepository;
        this.toolTypeRepository = toolTypeRepository;
        this.maintenanceRepository = maintenanceRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        getUsers();

    }

    private void getUsers() {

        ToolType toolType1 = new ToolType("tooltype1");

        ToolType savedTooltype1 = toolTypeRepository.save(toolType1);


        ToolType toolType2 = new ToolType("tooltype2");

        ToolType savedTooltype2 = toolTypeRepository.save(toolType2);

        User user1 = new User();
        Tool object1 = new Tool();
        object1.setToolType(savedTooltype1);
        object1.setUser(user1);

        user1.setFirstName("John");
        user1.setLastName("Marston");
        user1.getObjects().add(object1);

        userRepository.save(user1);


        User user2 = new User();
        Tool object2 = new Tool();
        object2.setToolType(savedTooltype2);
        object2.setUser(user2);

        user2.setFirstName("David");
        user2.setLastName("Marshall");
        user2.getObjects().add(object2);

        userRepository.save(user2);



        Maintenance maintenance1 = new Maintenance(LocalDate.of(2018,12,23), "loblar cokmus", object1);
        maintenanceRepository.save(maintenance1);
        Maintenance maintenance2 = new Maintenance(LocalDate.of(2018,12,30), "yanmıs", object1);

        maintenanceRepository.save(maintenance2);

        log.debug("Data Loading . ... ");

    }


}
