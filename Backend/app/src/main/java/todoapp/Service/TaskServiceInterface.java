package todoapp.Service;

import java.io.File;
import java.util.List;

public interface TaskServiceInterface {

    boolean verify(String name, File file);
    List<List<String>> dataToArray(File file);
    void sorter(String fieldToSort, File file, File tempfile);
    void search(String field, String name, File file);
    void count(File file);


}
