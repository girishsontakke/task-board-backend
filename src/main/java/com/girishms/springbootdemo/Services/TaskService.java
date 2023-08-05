package com.girishms.springbootdemo.Services;

import com.girishms.springbootdemo.Entities.Task;
import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
}
