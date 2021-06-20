package org.education.task.manager.service;

import org.education.task.manager.model.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface TaskService {

    TaskDto getById(Integer id);
    Collection<TaskDto> getAll();
    ResponseEntity deleteTaskById(Integer id);
    String insertTask(TaskDto dto);
    void updateTaskById(Integer id, TaskDto dto);
    Integer getId(TaskDto dto);
}
