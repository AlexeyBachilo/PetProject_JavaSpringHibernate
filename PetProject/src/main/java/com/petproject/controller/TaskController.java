package com.petproject.controller;

import com.petproject.entity.Task;
import com.petproject.service.TaskService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petproject/main")
public class TaskController {

    protected static Logger logger = LogManager.getLogger("TaskControllerLogger");

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/tasks")
    public String getTasks(Model model){
        logger.debug("Recieved request to show all tasks");
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskMenu";
    }

    @GetMapping(value = "/tasks/add")
    public String getAdd(Model model){
        logger.debug("Recieved request to show add tasl page");
        model.addAttribute("taskAttribute", new Task());
        return "addTask";
    }

    @PostMapping(value = "/tasks/add")
    public String add(@ModelAttribute("taskAttribute") Task task){
        logger.debug("Recieved request to add new task");
        taskService.addTask(task);
        return "addedTask";
    }

    @GetMapping(value = "/tasks/delete")
    public String delete(@RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to delete task");
        Task task = taskService.getTaskById(taskId);
        taskService.deleteTask(task);
        model.addAttribute("task", task);
        return "deletedTask";
    }

    @GetMapping(value = "/tasks/update")
    public String getUpdate(@RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to show update task page");
        model.addAttribute("taskAttribute", taskService.getTaskById(taskId));
        return "updateTask";
    }

    @PostMapping(value = "/tasks/update")
    public String saveUpdate(@ModelAttribute("taskAttribute") Task task, @RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to update task");
        task.setTaskId(taskId);
        taskService.updateTask(task);
        model.addAttribute("taskId", taskId);
        return "updatedTask";
    }
}
