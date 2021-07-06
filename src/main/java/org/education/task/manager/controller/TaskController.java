package org.education.task.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.education.task.manager.model.dto.TaskDto;
import org.education.task.manager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String greetingFromDocker(){
        return "Hello";
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Integer id) {
        return taskService.getById(id);
    }

    @GetMapping("/all")
    public Collection<TaskDto> getAllTasks() {
        return taskService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskById(@PathVariable Integer id) {
        return taskService.deleteTaskById(id);
    }

    @SneakyThrows
    @PostMapping("/insert")
    public ResponseEntity insertIntoTask(@ModelAttribute TaskDto taskDto) {
        String filePath = taskService.insertTask(taskDto);
        return new ResponseEntity(filePath, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateTaskById(@PathVariable Integer id, @ModelAttribute TaskDto taskDto) {
        taskService.updateTaskById(id, taskDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
