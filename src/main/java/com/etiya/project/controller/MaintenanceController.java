package com.etiya.project.controller;


import com.etiya.project.domain.Maintenance;
import com.etiya.project.domain.Tool;
import com.etiya.project.domain.User;
import com.etiya.project.service.MaintenanceService;
import com.etiya.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.DELETE,RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
@Slf4j
@Controller
public class MaintenanceController {


    private final MaintenanceService maintenanceService;
    private final UserService userService;

    public MaintenanceController(MaintenanceService maintenanceService, UserService userService) {
        this.maintenanceService = maintenanceService;
        this.userService = userService;
    }

    /**
     * @param id
     * @return Response
     */
    @GetMapping("maintenance/{id}")
    @ResponseBody
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable String id) {

        Maintenance maintenance = maintenanceService.findById(Long.valueOf(id));

        return ResponseEntity.ok().body(maintenance);


    }

    /**
     * @param userId
     * @return Response
     */
    @GetMapping("maintenance/user/{userId}")
    @ResponseBody
    public ResponseEntity<Set<Maintenance>> getMaintenanceSetByUserId(@PathVariable String userId) {

        User user = userService.findById(Long.valueOf(userId));
        Set<Tool> userTools = user.getObjects();
        Set<Maintenance> maintenanceSet = new HashSet<>();

        userTools.stream()
                .forEach(tool -> tool.getMaintenances()
                        .forEach(maintenance -> maintenanceSet.add(maintenance)));

        return ResponseEntity.ok().body(maintenanceSet);


    }

    /**
     * @return Response
     */
    @GetMapping("maintenance/all")
    public ResponseEntity<Set<Maintenance>> getMaintenanceList() {
        Set<Maintenance> maintenanceSet = maintenanceService.findAll();

        return ResponseEntity.ok().body(maintenanceSet);

    }

    /**
     * @param newMaintenance
     * @return Response
     */
    @PostMapping("maintenance")
    public ResponseEntity<Boolean> newMaintenance(@Valid @RequestBody Maintenance newMaintenance) {
        maintenanceService.save(newMaintenance);
        return ResponseEntity.ok().body(true);
    }


}
