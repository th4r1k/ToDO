package todoapp.Model.DAO;

import todoapp.Model.Entity.Task;

import java.util.List;

public interface TaskDAOInterface {

    List<Task> getAllTasks();
    void save(List<Task> taskList);

}
