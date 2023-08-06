package com.girishms.springbootdemo.repository;

import com.girishms.springbootdemo.Entities.Task;
import com.girishms.springbootdemo.Repositories.TaskRepository;
import com.girishms.springbootdemo.constants.TaskStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @Test
    public void taskRepository_saveTask_returnSavedTask(){
        // Arrange
        Task task = Task.builder()
                .title("test task")
                .description("description of test task")
                .status(TaskStatus.TODO)
                .build();

        // Act
        Task savedTask = taskRepository.save(task);

        // Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
    }

    @Test
    public void taskRepository_saveTask_rejectOnNullTitle(){
        // Arrange
        Task task = Task.builder()
                .description("description of test task")
                .status(TaskStatus.TODO)
                .build();

        // Act
        try {
            Task savedTask = taskRepository.save(task);
        }catch (Exception e) {
            // Assert
            Assertions.assertThat(e).isNotNull();
            Assertions.assertThat(e).isInstanceOf(DataIntegrityViolationException.class);
        }

    }
}
