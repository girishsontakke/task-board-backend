package com.girishms.springbootdemo.Services.impl;

import com.girishms.springbootdemo.Entities.Task;
import com.girishms.springbootdemo.Repositories.TaskRepository;
import com.girishms.springbootdemo.Services.TaskService;
import com.girishms.springbootdemo.constants.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;
    private TaskService taskService;

    @InjectMocks
    private Task task;
    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(taskRepository);
        task = Task.builder()
                .title("First task")
                .status(TaskStatus.TODO)
                .description("First task for test")
                .build();
    }

    @Test
    void testGetTasks() {
        when(taskRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(task)));
        List<Task> taskList = taskService.getTasks();
        assertThat(taskList.size()).isEqualTo(1);
        assertThat(taskList.get(0)).isEqualTo(task);
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        assertThat(taskService.getTaskById(1L)).isEqualTo(task);
    };

    @Test
    void testCreateTask() {
        when(taskRepository.save(task)).thenReturn(task);
        assertThat(taskService.createTask(task)).isEqualTo(task);
    }
}