package com.etiya.project.service.impl;

import com.etiya.project.domain.Tool;
import com.etiya.project.exception.NotFoundException;
import com.etiya.project.repository.ToolRepository;
import com.etiya.project.service.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;

    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }


    @Override
    public Set<Tool> findAll() {
        Set<Tool> toolSet = new HashSet<>();
        toolRepository.findAll().iterator().forEachRemaining(toolSet::add);

        return toolSet;
    }

    @Override
    public Tool findById(Long id) {
        log.debug(" Getting Tool by id : " + id);


        Optional<Tool> toolOptional = toolRepository.findById(id);

        if (!toolOptional.isPresent()) {
            throw new NotFoundException("Tool Not Found For ID Value: " + id.toString());
        }


        return toolOptional.get();
    }

    @Override
    public Tool save(Tool object) {
        log.debug("Saving Tool...");


        Tool savedTool = toolRepository.save(object);


        return savedTool;
    }

    @Override
    public void delete(Tool object) {
        log.debug("deleting object");
        toolRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        toolRepository.deleteById(id);
    }
}
