package utils;

import java.io.File;
import java.io.IOException;

public class Persist {
       public static void createFiles() {
        try {
            File path = new File("data");
            path.mkdir();
            File todos = new File("data/todos.csv");
            File alarms = new File("data/alarms.csv");
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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            // e.printStackTrace();
        }

    }
    
    
}
