package org.education.task.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.education.task.manager.model.domain.TaskEntity;
import org.education.task.manager.model.dto.TaskDto;
import org.education.task.manager.repository.TaskRepository;
import org.education.task.manager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public TaskDto getById(Integer id)
    {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        return modelMapper.map(taskEntity.get(),TaskDto.class);
    }

    @Override
    public Collection<TaskDto> getAll() {
        Collection<TaskEntity> listTasks = taskRepository.findAll();
        return listTasks.stream()
                        .map(x-> modelMapper.map(x,TaskDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity deleteTaskById(Integer id) {
        if(taskRepository.findById(id).isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @Override
    public void insertTask(TaskDto dto) {
        TaskEntity entity = modelMapper.map(dto,TaskEntity.class);
        taskRepository.save(entity);

    }

    @Override
    public void updateTaskById(Integer id, TaskDto dto) {
        dto.setId(id);
        TaskEntity entity = modelMapper.map(dto,TaskEntity.class);
        taskRepository.save(entity);

    }
}
