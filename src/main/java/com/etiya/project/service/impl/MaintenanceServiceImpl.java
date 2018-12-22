package com.etiya.project.service.impl;

import com.etiya.project.domain.Maintenance;
import com.etiya.project.exception.NotFoundException;
import com.etiya.project.repository.MaintenanceRepository;
import com.etiya.project.service.MaintenanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;


    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;

    }

    @Override
    public Set<Maintenance> findAll() {
        Set<Maintenance> maintenanceSet = new HashSet<>();
        maintenanceRepository.findAll().iterator().forEachRemaining(maintenanceSet::add);

        return maintenanceSet;
    }

    @Override
    public Maintenance findById(Long id) {
        log.debug(" Getting Maintenance by id : " + id);


        Optional<Maintenance> maintenanceOptional = maintenanceRepository.findById(id);

        if (!maintenanceOptional.isPresent()) {
            throw new NotFoundException("Maintenance Not Found For ID Value: " + id.toString());
        }


        return maintenanceOptional.get();
    }

    @Override
    public Maintenance save(Maintenance object) {
        log.debug("Saving Maintenance...");

        return maintenanceRepository.save(object);
    }

    @Override
    public void delete(Maintenance object) {
        log.debug("deleting object");
        maintenanceRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);

    }
}
