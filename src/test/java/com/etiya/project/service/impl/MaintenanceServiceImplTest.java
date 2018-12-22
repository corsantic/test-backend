package com.etiya.project.service.impl;

import com.etiya.project.domain.Maintenance;
import com.etiya.project.repository.MaintenanceRepository;
import com.etiya.project.service.MaintenanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaintenanceServiceImplTest {

    MaintenanceService maintenanceService;

    @Mock
    MaintenanceRepository maintenanceRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        maintenanceService = new MaintenanceServiceImpl(maintenanceRepository);

    }

    @Test
    void findAll() {
        Maintenance maintenance =new Maintenance();
        Set<Maintenance> maintenanceSet = new HashSet<>();

        maintenanceSet.add(maintenance);


        when(maintenanceRepository.findAll()).thenReturn(maintenanceSet);


        Set<Maintenance> maintenances = maintenanceService.findAll();


        assertEquals(maintenances.size(),1);
        verify(maintenanceRepository,times(1)).findAll();
        verify(maintenanceRepository,never()).findById(anyLong());



    }

    @Test
    void findById() {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(1L);

        Optional<Maintenance> maintenanceOptional = Optional.of(maintenance);

        when(maintenanceRepository.findById(anyLong())).thenReturn(maintenanceOptional);

        Maintenance maintenanceReturned = maintenanceService.findById(1L);

        assertNotNull(maintenanceReturned,"Null Maintenance Returned");
        verify(maintenanceRepository,times(1)).findById(anyLong());
        verify(maintenanceRepository,never()).findAll();








    }

    @Test
    void save() {
        Maintenance maintenance =  new Maintenance();

        maintenance.setId(1L);

        when(maintenanceService.save(any())).thenReturn(maintenance);

        Maintenance savedMaintenance = maintenanceService.save(maintenance);

        assertNotNull(savedMaintenance,"Null Maintenance");
        verify(maintenanceRepository,times(1)).save(any());
    }

    @Test
    void delete() {
        Maintenance maintenanceToDelete =new Maintenance();
        maintenanceToDelete.setId(1L);


        maintenanceRepository.delete(maintenanceToDelete);

        verify(maintenanceRepository,times(1)).delete(any());

    }

    @Test
    void deleteById() {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        maintenanceRepository.deleteById(idToDelete);


        //then
        verify(maintenanceRepository, times(1)).deleteById(anyLong());
    }
}