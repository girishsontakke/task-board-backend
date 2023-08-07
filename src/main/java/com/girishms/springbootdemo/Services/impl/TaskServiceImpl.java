package com.girishms.springbootdemo.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.girishms.springbootdemo.Entities.Task;
import com.girishms.springbootdemo.Repositories.TaskRepository;
import com.girishms.springbootdemo.Services.TaskService;
import com.girishms.springbootdemo.exceptions.CustomException;
import com.girishms.springbootdemo.exceptions.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        if(Objects.isNull(task.getId())){
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Task existingTask = getTaskById(task.getId());
        if(Objects.isNull(existingTask)) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        try{
            Task updatedTask = objectMapper.readerForUpdating(existingTask)
                    .readValue(objectMapper.writeValueAsString(task));
            return taskRepository.save(updatedTask);
        }catch (JsonProcessingException e){
            throw new CustomException(ErrorCode.JSON_MAPPING_EXCEPTION);
        }
    }
}
