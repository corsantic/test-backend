package com.etiya.project.service.impl;

import com.etiya.project.domain.ToolType;
import com.etiya.project.repository.ToolTypeRepository;
import com.etiya.project.service.ToolTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ToolTypeSterviceImplTest {
    ToolTypeService toolTypeService;

    @Mock
    ToolTypeRepository toolTypeRepository;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toolTypeService = new ToolTypeServiceImpl(toolTypeRepository);

    }

    @Test
    void findAll() {
        ToolType toolType =  new ToolType();

        Set<ToolType> toolTypeSet = new HashSet<>();

        toolTypeSet.add(toolType);

        when(toolTypeRepository.findAll()).thenReturn(toolTypeSet);

        Set<ToolType> toolTypes = toolTypeService.findAll();

        assertEquals(toolTypes.size(),1);
        verify(toolTypeRepository,times(1)).findAll();
        verify(toolTypeRepository,never()).findById(anyLong());

     }

    @Test
    void findById() {
        ToolType toolType =new ToolType();
        toolType.setId(1L);

        Optional<ToolType> toolOptional = Optional.of(toolType);

        when(toolTypeRepository.findById(anyLong())).thenReturn(toolOptional);

        ToolType toolTypeReturned = toolTypeService.findById(1L);

        assertNotNull(toolTypeReturned,"Null ToolType Returned");
        verify(toolTypeRepository,times(1)).findById(anyLong());
        verify(toolTypeRepository,never()).findAll();
    }

    @Test
    void save() {
        ToolType toolType =  new ToolType();

        toolType.setId(1L);

        when(toolTypeService.save(any())).thenReturn(toolType);

        ToolType savedToolType = toolTypeService.save(toolType);

        assertNotNull(savedToolType,"Null ToolType");
        verify(toolTypeRepository,times(1)).save(any());

    }

    @Test
    void delete() {
        ToolType toolTypeToDelete =new ToolType();
        toolTypeToDelete.setId(1L);


        toolTypeRepository.delete(toolTypeToDelete);

        verify(toolTypeRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        toolTypeRepository.deleteById(idToDelete);


        //then
        verify(toolTypeRepository, times(1)).deleteById(anyLong());
    }
}