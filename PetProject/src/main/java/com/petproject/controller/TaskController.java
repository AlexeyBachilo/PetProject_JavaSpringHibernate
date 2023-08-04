package com.petproject.controller;

import com.petproject.entity.Task;
import com.petproject.service.TaskService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/petproject/main")
public class TaskController {

    protected static Logger logger = LogManager.getLogger("TaskControllerLogger");

    @Autowired
    TaskService taskService;

    @ModelAttribute("taskAttribute")
    public List<Task> populateTasks(){
        return this.taskService.getAllTasks();
    }

    @GetMapping(value = "/tasks")
    public String getTasks(){
        logger.debug("Recieved request to show all tasks");
        populateTasks();
        return "taskMenu";
    }

    @GetMapping(value = "/tasks/add")
    public String addTask(){
        logger.debug("Recieved request to show add tasl page");
        populateTasks();
        return "addTask";
    }

    @PostMapping(value = "/tasks/add", params = {"Save"})
    public String addedTask(final Task task, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to add new task");
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        this.taskService.addTask(task);
        return "addedTask";
    }

    @GetMapping(value = "/tasks/delete")
    public String deleteTask(final Long taskId, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to delete task");
        if(bindingResult.hasErrors()){
            return "taskMenu";
        }
        Task task = this.taskService.getTaskById(taskId);
        this.taskService.deleteTask(task);
        model.clear();
        return "deletedTask";
    }

    @GetMapping(value = "/tasks/update")
    public String updateTask(){
        logger.debug("Recieved request to show update task page");
        populateTasks();
        return "updateTask";
    }

    @PostMapping(value = "/tasks/update")
    public String updatedTask(final Task task, @RequestParam(value = "taskId")final Long taskId, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to update task");
        if(bindingResult.hasErrors()){
            return "updateTask";
        }
        task.setTaskId(taskId);
        this.taskService.updateTask(task);
        model.clear();
        return "updatedTask";
    }
}
