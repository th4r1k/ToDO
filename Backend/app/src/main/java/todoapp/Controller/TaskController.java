package todoapp.Controller;

import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.TaskDAOInterface;
import todoapp.Model.Service.TaskService;

public class TaskController {
    TaskDAOInterface taskDAO;
    TaskService taskService;

    public TaskController(TaskDAOInterface taskDAO, TaskService taskService) {
        this.taskDAO = taskDAO;
        this.taskService = taskService;
    }

    public void getAllTasks(){
        taskService.taskList = taskDAO.getAllTasks();
    }

    public void save(){
        taskDAO.save(taskService.taskList);
    }

    public void addTask(Task task){
        taskService.addTask(task);
    }

    public void removeTask(Task task){
        taskService.removeTask(task);
    }

    public boolean verifyTaskExist(String taskName){
        return taskService.verifyExists(taskName);
    }

    public Task getTaskByName(String taskName){
        return taskService.getTaskByName(taskName);
    }

    public void search(String fieldToSearch, String name){
        taskService.search(fieldToSearch, name);
    }

    public void sorter(String fieldToSort){
        taskService.sorter(fieldToSort);
    }

    public void sortByPriority(){
        taskService.sortByPriority();
    }

    public void countTasks(){
        taskService.count();
    }

    public void printAllTasks(){
        for(Task task : taskService.taskList){
            System.out.println(task);
        }
    }
}
