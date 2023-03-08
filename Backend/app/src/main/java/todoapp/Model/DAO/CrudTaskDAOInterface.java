package todoapp.Model.DAO;

import todoapp.Model.Entity.Task;

import java.io.File;

public interface CrudTaskDAOInterface {
    boolean create(Task task, File file);
    boolean read(File file);
    boolean update(String name, int field, String newData, File file, File tempfile);
    boolean delete(String name, File file, File tempfile);

}
