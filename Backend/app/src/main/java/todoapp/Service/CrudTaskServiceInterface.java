package todoapp.Service;

import todoapp.Entity.Task;

import java.io.File;

public interface CrudTaskServiceInterface {
    void create(Task task, File file);
    void read(File file);
    void update(String name, int field, String newData, File file, File tempfile);
    void delete(String name, File file, File tempfile);

}
