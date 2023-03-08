package todoapp.Controller;

import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.CrudTaskDAOInterface;

import java.io.File;

public class CrudTaskController {

    CrudTaskDAOInterface crudTaskService;

    public CrudTaskController(CrudTaskDAOInterface crudTaskService) {
        this.crudTaskService = crudTaskService;
    }

    public boolean createTask(Task task, File file) {
       return crudTaskService.create(task, file);
    }

    public boolean readAllTasks(File file) {
        return crudTaskService.read(file);
    }

    public boolean updateTask(String name, int field, String newData, File file, File tempfile) {
       return crudTaskService.update(name, field, newData, file, tempfile);
    }

    public boolean deleteTask(String name, File file, File tempfile) {
        return crudTaskService.delete(name, file, tempfile);
    }
}
