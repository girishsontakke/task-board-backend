package com.girishms.springbootdemo.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.girishms.springbootdemo.Entities.Task;
import com.girishms.springbootdemo.Repositories.TaskRepository;
import com.girishms.springbootdemo.constants.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;
    @Spy
    private ObjectMapper objectMapper;
    @InjectMocks
    private TaskServiceImpl taskService;
    private Task task;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id(1L)
                .title("First task")
                .status(TaskStatus.TODO)
                .description("First task for test")
                .build();
    }

    @AfterEach
    void tearDown() {
        task = null;
    }

    @Test
    void testGetTasks() {
        when(taskRepository.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(task)));
        List<Task> taskList = taskService.getTasks();
        assertThat(taskList.size()).isEqualTo(1);
        assertThat(taskList.get(0)).isEqualTo(task);
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        assertThat(taskService.getTaskById(1L)).isEqualTo(task);
    }


    @Test
    void testCreateTask() {
        when(taskRepository.save(task)).thenReturn(task);
        assertThat(taskService.createTask(task)).isEqualTo(task);
    }

    @Test
    void testUpdateTask() throws JsonProcessingException {
        mock(ObjectMapper.class, CALLS_REAL_METHODS);
        Task updatedTask = Task.builder()
                .id(task.getId())
                .title("Updated title")
                .build();
        when(taskRepository.findById(task.getId())).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        Task result = taskService.updateTask(updatedTask);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated title");

        // asserting that only provided fields in the updatedTask object is updated in the existing task;
        assertThat(result.getDescription()).isEqualTo("First task for test");
    }
}