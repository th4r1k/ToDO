package todoapp.Model.DAO;

import java.io.*;
import java.util.*;

import todoapp.Model.Entity.Task;

public class TaskDAO implements TaskDAOInterface {
    final private File taskFile = new File("data/tasks.csv");

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(taskFile);
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] array = line.split((","));
                Task task = new Task(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
                taskList.add(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel ler o arquivo de tarefas e converter para arrays");
        }
        return taskList;
    }

    public void save(List<Task> taskList) {
        taskFile.delete();
        try (FileWriter pw = new FileWriter(taskFile, true)) {

                pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
                pw.append("\n");

            for(Task task : taskList){
                pw.write(task.toString());
                pw.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
