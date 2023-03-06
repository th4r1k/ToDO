package todoapp.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Persist {
    public static void createFiles() {
        try {
            File path = new File("data");
            path.mkdir();
            File tasks = new File("data/tasks.csv");
            File alarms = new File("data/alarms.csv");
            if (tasks.createNewFile()) {
                System.out.println("File created: " + tasks.getName());
            }
            if (alarms.createNewFile()) {
                System.out.println("File created: " + alarms.getName());
            }
            createFilesHeaders(tasks, alarms);

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void createFilesHeaders(File tasks, File alarms) throws IOException {
        FileWriter pw = new FileWriter(tasks, true);

        if (tasks.length() == 0) {
            pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
            pw.append("\n");
        }
        if (alarms.length() == 0) {
            pw.write("ToDo`s Name" + "," + "AlarmDate" + "," + "AlarmTime" + "," + "EndDate" + "," + "EndTime" + "," + "Priority");
            pw.append("\n");
        }
        pw.close();
    }
}
