package todoapp.Controller;

import todoapp.Service.TaskServiceInterface;

import java.io.File;
import java.util.List;

public class TaskController {

    TaskServiceInterface taskService;

    public TaskController(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }

    public boolean taskExist(String name, File file) {
        return taskService.verify(name, file);
    }

    public List<List<String>> listAllTask(File file) {
        return taskService.dataToArray(file);
    }

    public void sortTask(String fieldToSort, File file, File tempfile) {
        taskService.sorter(fieldToSort, file, tempfile);
    }

    public void searchTask(String field, String name, File file) {
        taskService.search(field, name, file);
    }

    public void countTasks(File file) {
        taskService.count(file);
    }
}
