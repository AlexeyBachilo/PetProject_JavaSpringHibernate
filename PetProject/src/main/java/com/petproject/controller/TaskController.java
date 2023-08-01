package com.petproject.controller;

import com.petproject.entity.Task;
import com.petproject.service.TaskService;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/main")
public class TaskController {

    protected static Logger logger;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getTasks(Model model){
        logger.debug("Recieved request to show all tasks");
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskMenu";
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.GET)
    public String getAdd(Model model){
        logger.debug("Recieved request to show add tasl page");
        model.addAttribute("taskAttribute", new Task());
        return "addTask";
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("taskAttribute") Task task){
        logger.debug("Recieved request to add new task");
        taskService.addTask(task);
        return "addedTask";
    }

    @RequestMapping(value = "/tasks/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to delete task");
        Task task = taskService.getTaskById(taskId);
        taskService.deleteTask(task);
        model.addAttribute("task", task);
        return "deletedTask";
    }

    @RequestMapping(value = "/tasks/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to show update task page");
        model.addAttribute("taskAttribute", taskService.getTaskById(taskId));
        return "updateTask";
    }

    @RequestMapping(value = "/tasks/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("taskAttribute") Task task, @RequestParam(value = "taskId") Long taskId, Model model){
        logger.debug("Recieved request to update task");
        task.setTaskId(taskId);
        taskService.updateTask(task);
        model.addAttribute("taskId", taskId);
        return "updatedTask";
    }
}
