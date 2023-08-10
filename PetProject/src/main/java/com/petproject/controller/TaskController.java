package com.petproject.controller;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/petproject/main")
public class TaskController {

    protected static Logger logger = LogManager.getLogger("TaskControllerLogger");

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @ModelAttribute("tasksAttribute")
    public List<Task> populateTasks(){
        return this.taskService.getAllTasks();
    }

    @ModelAttribute("newTaskAttribute")
    public Task newTask(@RequestParam(value = "userId", required = false, defaultValue = "1")Long userId){
        Task newTask = new Task();
        if(userId != null){
            newTask.setUser(userService.getUserById(userId));
        }
        return newTask;
    }
//
    @ModelAttribute("UDTaskAttribute")
    public Task UDTask(@RequestParam(value = "id", required = false, defaultValue = "1")Long taskId){
        return taskService.getTaskById(taskId);
    }
//
    @GetMapping(value = "/tasks")
    public String getTasks(){
        logger.debug("Recieved request to show all tasks");
        return "taskMenu";
    }

    @GetMapping(value = "/tasks/add")
    public String addTask(Model model){
        logger.debug("Recieved request to show add task page");
        List<User> options = userService.getAllUsers();
        model.addAttribute("options",options);
        return "addTask";
    }

    @PostMapping(value = "/tasks/add")
    public String addedTask(final Task task, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to add new task");
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        this.taskService.addTask(task);
        return "addedTask";
    }

    @GetMapping(value = "/tasks/delete")
    public String deleteTask(@RequestParam(value = "id") final Long taskId, final ModelMap model){
        logger.debug("Recieved request to delete task");
        Task task = this.taskService.getTaskById(taskId);
        this.taskService.deleteTask(task);
        model.clear();
        return "deletedTask";
    }

    @GetMapping(value = "/tasks/update")
    public String updateTask(Model model){
        logger.debug("Recieved request to show update task page");
        List<User> options = userService.getAllUsers();
        model.addAttribute("options",options);
        return "updateTask";
    }

    @PostMapping(value = "/tasks/update")
    public String updatedTask( @RequestParam(value = "id")final Long taskId, final Task task, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to update task");
        if(bindingResult.hasErrors()){
            return "updateTask";
        }
        task.setTaskId(taskId);
        this.taskService.updateTask(task);
        model.addAttribute("taskId", taskId);
        return "updatedTask";
    }
}
