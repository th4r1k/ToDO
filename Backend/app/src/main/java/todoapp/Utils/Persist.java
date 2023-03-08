package todoapp.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Persist {
    public static void createTaskFile() {
        try {
            File path = new File("data");
            path.mkdir();
            File tasksFile = new File("data/tasks.csv");

            if (tasksFile.createNewFile()) {
                System.out.println("File created: " + tasksFile.getName());
            }
            createTaskFileHeader(tasksFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void createAlarmFile() {
        try {
            File path = new File("data");
            path.mkdir();
            File alarmsFile = new File("data/alarms.csv");

            if (alarmsFile.createNewFile()) {
                System.out.println("File created: " + alarmsFile.getName());
            }
            createAlarmFileHeader(alarmsFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void createTaskFileHeader(File tasksFile) throws IOException {
        FileWriter pw = new FileWriter(tasksFile, true);
        if (tasksFile.length() == 0) {
            pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
            pw.append("\n");
        }
        pw.close();
    }
    public static void createAlarmFileHeader(File alarmsFile) throws IOException {
        FileWriter pw = new FileWriter(alarmsFile, true);
        if (alarmsFile.length() == 0) {
            pw.write("ToDo`s Name" + "," + "AlarmDate" + "," + "AlarmTime" + "," + "EndDate" + "," + "EndTime" + "," + "Priority");
            pw.append("\n");
        }
        pw.close();
    }
}
