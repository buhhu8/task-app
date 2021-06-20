package org.education.task.manager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.education.task.manager.model.domain.TaskEntity;
import org.education.task.manager.model.dto.TaskDto;
import org.education.task.manager.repository.TaskRepository;
import org.education.task.manager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public TaskDto getById(Integer id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        return modelMapper.map(taskEntity.get(), TaskDto.class);
    }

    @Override
    public Collection<TaskDto> getAll() {
        Collection<TaskEntity> listTasks = taskRepository.findAll();
        return listTasks.stream()
                .map(x -> modelMapper.map(x, TaskDto.class))
                .collect(toList());
    }

    @SneakyThrows
    @Override
    public ResponseEntity deleteTaskById(Integer id) {
        if (taskRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            taskRepository.deleteById(id);
            Files.delete(Paths.get("F:\\test\\" + id));
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @SneakyThrows
    @Override
    public String insertTask(TaskDto dto) {
        TaskEntity entity = modelMapper.map(dto, TaskEntity.class);
        Integer id = getId(dto) + 1;
        String filePath = "F:\\test\\" + id;
        entity.setTaskLink(filePath);
        taskRepository.save(entity);
        Files.copy(dto.getFile().getInputStream(), Paths.get(filePath));
        return filePath;

    }

    @SneakyThrows
    @Override
    public void updateTaskById(Integer id, TaskDto dto) {
        dto.setId(id);
        TaskEntity entity = modelMapper.map(dto, TaskEntity.class);
        taskRepository.save(entity);
        String filePath = "F:\\test\\" + id;
        Files.copy(dto.getFile().getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

    }

    @Override
    public Integer getId(TaskDto dto) {
        Collection<TaskDto> taskDtos = getAll();

        return taskDtos.stream()
                .map(x -> x.getId())
                .reduce(Integer.MIN_VALUE, Integer::max);
    }
}
