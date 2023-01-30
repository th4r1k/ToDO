package src.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Persist {
    public static void createFiles() {
        try {
            File path = new File("data");
            path.mkdir();
            File todos = new File("data/todos.csv");
            File alarms = new File("data/alarms.csv");
            FileWriter pw = new FileWriter(todos, true);
            if (todos.createNewFile()) {
                System.out.println("File created: " + todos.getName());
            }
            // else {
            //     System.out.println("File already exists.");
            // }
            if (alarms.createNewFile()) {
                System.out.println("File created: " + alarms.getName());
            }
            // else {
            //     System.out.println("File already exists.");
            // }
                if (todos.length() == 0) {
                    pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
                    pw.append("\n");
                }
            pw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            // e.printStackTrace();
        }
    }
}
