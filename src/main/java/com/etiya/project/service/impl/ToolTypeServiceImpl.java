package com.etiya.project.service.impl;

import com.etiya.project.domain.ToolType;
import com.etiya.project.exception.NotFoundException;
import com.etiya.project.repository.ToolTypeRepository;
import com.etiya.project.service.ToolTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class ToolTypeServiceImpl implements ToolTypeService {
    private final ToolTypeRepository toolTypeRepository;

    public ToolTypeServiceImpl(ToolTypeRepository toolTypeRepository) {
        this.toolTypeRepository = toolTypeRepository;
    }

    /**
     *
     * @return Set<ToolType>
     */

    @Override
    public Set<ToolType> findAll() {
        Set<ToolType> toolTypeSet = new HashSet<>();
        toolTypeRepository.findAll().iterator().forEachRemaining(toolTypeSet::add);

        return toolTypeSet;
    }

    /**
     *
     * @param id
     * @return ToolType
     */
    @Override
    public ToolType findById(Long id) {
        log.debug(" Getting ToolType by id : " + id);


        Optional<ToolType> toolTypeOptional = toolTypeRepository.findById(id);

        if (!toolTypeOptional.isPresent()) {
            throw new NotFoundException("ToolType Not Found For ID Value: " + id.toString());
        }


        return toolTypeOptional.get();
    }

    /**
     *
     * @param object
     * @return ToolType
     */
    @Override
    public ToolType save(ToolType object) {
        log.debug("Saving ToolType...");


        ToolType savedToolType = toolTypeRepository.save(object);


        return savedToolType;
    }

    /**
     *
     * @param object
     */
    @Override
    public void delete(ToolType object) {
        log.debug("deleting object");
        toolTypeRepository.delete(object);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {

        toolTypeRepository.deleteById(id);

    }
}
