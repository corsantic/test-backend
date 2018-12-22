package com.etiya.project.controller;


import com.etiya.project.domain.ToolType;
import com.etiya.project.service.ToolTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.DELETE,RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
@Slf4j
@Controller
public class ToolTypeController {

    private final ToolTypeService toolTypeService;

    public ToolTypeController(ToolTypeService toolTypeService) {
        this.toolTypeService = toolTypeService;
    }

    @PostMapping("type")
    public ResponseEntity<Boolean> newToolType(@Valid @RequestBody ToolType toolType) {
        toolTypeService.save(toolType);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("type/all")
    @ResponseBody
    public ResponseEntity<Set<ToolType>> getTools() {

        Set<ToolType> toolTypeSet = toolTypeService.findAll();

        return ResponseEntity.ok().body(toolTypeSet);


    }

}
