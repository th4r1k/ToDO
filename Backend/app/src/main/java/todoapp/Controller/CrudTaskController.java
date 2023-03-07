package todoapp.Controller;

import todoapp.Model.Entity.Task;
import todoapp.Model.Service.CrudTaskServiceInterface;

import java.io.File;

public class CrudTaskController {

    CrudTaskServiceInterface crudTaskService;

    public CrudTaskController(CrudTaskServiceInterface crudTaskService) {
        this.crudTaskService = crudTaskService;
    }

    public void createTask(Task task, File file) {
        crudTaskService.create(task, file);
    }

    public void readAllTasks(File file) {
        crudTaskService.read(file);
    }

    public void updateTask(String name, int field, String newData, File file, File tempfile) {
        crudTaskService.update(name, field, newData, file, tempfile);
    }

    public void deleteTask(String name, File file, File tempfile) {
        crudTaskService.delete(name, file, tempfile);
    }
}
