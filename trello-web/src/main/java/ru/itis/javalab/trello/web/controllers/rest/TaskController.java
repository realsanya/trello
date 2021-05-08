package ru.itis.javalab.trello.web.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.trello.api.dto.ProjectDto;
import ru.itis.javalab.trello.api.dto.TaskDto;
import ru.itis.javalab.trello.api.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "Получение проектов по id пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно найдена", response = TaskDto.class)})
    @GetMapping("/all")
    public Page<TaskDto> findAllByDashboardID(@RequestParam Long dashboardId, Pageable pageable){
        return taskService.getByDashboardId(dashboardId, pageable);
    }

    @ApiOperation(value = "Добавление новой задачи")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно сохранен", response = TaskDto.class)})
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TaskDto taskDto){
        taskService.addTask(taskDto);
        return ResponseEntity.ok("Success");
    }

}
