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

    public static void update(String name, int field, String newData) {
        File file = new File("data/todos.csv");
        File tempfile = new File("data/temptodos.csv");
        try {
            FileWriter pw = new FileWriter(tempfile, true);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] allParams = line.split(",");
                String firstParam = allParams[0];
                if (!firstParam.equals(name)) {
                    pw.write(line);
                    pw.append("\n");
                } else {
                    for (int i = 0; i <= 5; i++) {

                        if (i == field) {
                            pw.write(newData);
                            pw.append(",");

                        } else {
                            pw.write(allParams[i]);
                            pw.append(",");
                        }
                    }
                    pw.append("\n");
//
                }
            }
            reader.close();
            pw.close();
            file.delete();
            tempfile.renameTo(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void delete(String name) {

        File file = new File("data/todos.csv");
        File tempfile = new File("data/temptodos.csv");
        try {
            FileWriter pw = new FileWriter(tempfile, true);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (!firstParam.equalsIgnoreCase(name)) {
//                if(!line.contains(name)){
                    pw.write(line);
                    pw.append("\n");
                }
            }
            reader.close();
            pw.close();
            file.delete();
            tempfile.renameTo(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
