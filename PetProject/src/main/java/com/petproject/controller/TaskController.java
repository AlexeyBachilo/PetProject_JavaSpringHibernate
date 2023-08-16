package com.petproject.controller;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/petproject")
public class TaskController {

    protected static Logger logger = LogManager.getLogger("TaskControllerLogger");
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @ModelAttribute("newTaskAttribute")
    public Task newTask(@RequestParam(value = "userId", required = false, defaultValue = "1")Long userId){
        Task newTask = new Task();
        if(userId != null){
            newTask.setUser(userService.getUserById(userId));
        }
        return newTask;
    }

    @ModelAttribute("UDTaskAttribute")
    public Task UDTask(@RequestParam(value = "id", required = false, defaultValue = "1")Long taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping(value = "/admin/tasks")
    public String getTasks(Model model){
        logger.debug("ADMIN: Recieved request to show all tasks");
        model.addAttribute("role", "admin");
        model.addAttribute("tasksAttribute", this.taskService.getAllTasks());
        return "taskMenu";
    }

    @GetMapping(value = "/user/tasks")
    public String getUserTasks(Principal username, Model model){
        logger.debug("USER: Recieved request to show tasks");
        model.addAttribute("role", "user");
        User user = userService.getUserByEmail(username.getName());
        model.addAttribute("tasksAttribute", taskService.getTasksByUser(user));
        return "taskMenu";
    }

    @GetMapping(value = "/admin/tasks/add")
    public String addTask(Model model){
        logger.debug("ADMIN: Recieved request to show add task page");
        model.addAttribute("role", "admin");
        List<User> options = userService.getAllUsers();
        model.addAttribute("options",options);
        return "addTask";
    }

    @GetMapping(value = "/user/tasks/add")
    public String addUserTask(Principal username, Model model){
        logger.debug("USER: Recieved request to show add task page");
        model.addAttribute("role", "user");
        User user = userService.getUserByEmail(username.getName());
        model.addAttribute("options", user);
        return "addTask";
    }

    @PostMapping(value = {"/admin/tasks/add", "/user/tasks/add"})
    public String addedTask(final Task task, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to add new task");
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        this.taskService.addTask(task);
        return "addedTask";
    }

    @GetMapping(value = {"/admin/tasks/delete","/user/tasks/delete"})
    public String deleteTask(@RequestParam(value = "id") final Long taskId, final ModelMap model){
        logger.debug("Recieved request to delete task");
        Task task = this.taskService.getTaskById(taskId);
        this.taskService.deleteTask(task);
        model.clear();
        return "deletedTask";
    }

    @GetMapping(value = "/admin/tasks/update")
    public String updateTask(Model model){
        logger.debug("ADMIN: Recieved request to show update task page");
        model.addAttribute("role", "admin");
        List<User> options = userService.getAllUsers();
        model.addAttribute("options",options);
        return "updateTask";
    }

    @GetMapping(value = "/user/tasks/update")
    public String updateUserTask(Principal username, Model model){
        logger.debug("USER: Recieved request to update task");
        model.addAttribute("role", "user");
        User user = userService.getUserByEmail(username.getName());
        model.addAttribute("options", user);
        return "updateTask";
    }

    @PostMapping(value = {"/admin/tasks/update", "/user/tasks/update"})
    public String updatedTask(@RequestParam(value = "id")final Long taskId, final Task task, final BindingResult bindingResult, final ModelMap model){
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
