package Service;

import Entity.Todo;
import java.io.*;
import java.util.*;


public class Crud {

    public static void create(Todo todo) {

        File file = new File("data/todos.csv");
        try (FileWriter pw = new FileWriter(file, true)) {

            if (file.length() == 0) {
                pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "Priority" + "," + "Category" + "," + "Status");
                pw.append("\n");
            }
            pw.write(todo.getName() + ",");
            pw.write(todo.getDescription() + ",");
            pw.write(todo.getEndDate() + ",");
            pw.write(todo.getPriority() + ",");
            pw.write(todo.getCategory() + ",");
            pw.write(todo.getStatus());
            pw.append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read() {
        File file = new File("data/todos.csv");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
