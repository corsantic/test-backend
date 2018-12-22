package com.etiya.project.service.impl;

import com.etiya.project.domain.Tool;
import com.etiya.project.repository.ToolRepository;
import com.etiya.project.service.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ToolServiceImplTest {

    ToolService toolService;

    @Mock
    ToolRepository toolRepository;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);


        toolService = new ToolServiceImpl(toolRepository);


    }

    @Test
    void findAll() {
        Tool tool = new Tool();
        Set<Tool> toolSet =new HashSet<>();

        toolSet.add(tool);

        when(toolRepository.findAll()).thenReturn(toolSet);

        Set<Tool> tools = toolService.findAll();

        assertEquals(tools.size(),1);
        verify(toolRepository,times(1)).findAll();
        verify(toolRepository,never()).findById(anyLong());

    }

    @Test
    void findById() {
        Tool tool =new Tool();
        tool.setId(1L);

        Optional<Tool> toolOptional = Optional.of(tool);

        when(toolRepository.findById(anyLong())).thenReturn(toolOptional);

        Tool toolReturned = toolService.findById(1L);

        assertNotNull(toolReturned,"Null Tool Returned");
        verify(toolRepository,times(1)).findById(anyLong());
        verify(toolRepository,never()).findAll();
    }

    @Test
    void save() {
        Tool tool =  new Tool();

        tool.setId(1L);

        when(toolService.save(any())).thenReturn(tool);

        Tool savedTool = toolService.save(tool);

        assertNotNull(savedTool,"Null Tool");
        verify(toolRepository,times(1)).save(any());


    }

    @Test
    void delete() {
        Tool toolToDelete =new Tool();
        toolToDelete.setId(1L);


        toolRepository.delete(toolToDelete);

        verify(toolRepository,times(1)).delete(any());


    }

    @Test
    void deleteById() {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        toolRepository.deleteById(idToDelete);


        //then
        verify(toolRepository, times(1)).deleteById(anyLong());
    }
}