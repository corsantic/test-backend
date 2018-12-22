package com.etiya.project.controller;


import com.etiya.project.domain.Tool;
import com.etiya.project.service.ToolService;
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
public class ToolController {

    private final ToolService toolService;
    private final UserService userService;

    public ToolController(ToolService toolService, UserService userService) {
        this.toolService = toolService;
        this.userService = userService;
    }

    /**
     * list
     *
     * @return list
     */
    @GetMapping("tool/all")
    @ResponseBody
    public ResponseEntity<Set<Tool>> getTools() {

        Set<Tool> toolSet = toolService.findAll();

        return ResponseEntity.ok().body(toolSet);


    }

    /**
     * @param userId
     * @return user toollist
     */
    @GetMapping("tool/user/{userId}")
    @ResponseBody
    public ResponseEntity<Set<Tool>> getToolsByUserId(@PathVariable String userId) {

        Set<Tool> tools = new HashSet<>();

        userService.findById(Long.valueOf(userId)).getObjects()
                .stream()
                .forEach(tools::add);



        return ResponseEntity.ok().body(tools);


    }

    /**
     * @param id
     * @return tool by id
     */
    @GetMapping("tool/{id}")
    @ResponseBody
    public ResponseEntity<Tool> getToolsById(@PathVariable String id) {

        Tool tool = toolService.findById(Long.valueOf(id));

        return ResponseEntity.ok().body(tool);


    }


    @PostMapping("tool")
    public ResponseEntity<Boolean> saveTool(@Valid @RequestBody Tool tool) {

        toolService.save(tool);
        return ResponseEntity.ok().body(true);


    }


}
