package todoapp.Service;

import java.io.*;
import java.util.*;


import todoapp.Entity.Task;

public class CrudTaskService implements CrudTaskServiceInterface {

    public void create(Task task, File file) {

        try (FileWriter pw = new FileWriter(file, true)) {
            if (file.length() == 0) {
                pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
                pw.append("\n");
            }
            pw.write(task.getName() + ",");
            pw.write(task.getDescription() + ",");
            pw.write(task.getEndDate() + ",");
            pw.write(task.getEndTime() + ",");
            pw.write(task.getPriority() + ",");
            pw.write(task.getCategory() + ",");
            pw.write(task.getStatus());
            pw.append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void read(File file) {
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

    public void update(String name, int field, String newData, File file, File tempfile) {
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
                    for (int i = 0; i <= 6; i++) {

                        if (i == field) {
                            if (i == 6) {
                                pw.write(newData);
                            } else {
                                pw.write(newData);
                                pw.append(",");
                            }
                        } else {
                            if (i == 6) {
                                pw.write(allParams[i]);
                            } else {
                                pw.write(allParams[i]);
                                pw.append(",");
                            }
                        }
                    }
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

    public void delete(String name, File file, File tempfile) {
        try {
            FileWriter pw = new FileWriter(tempfile, true);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (!firstParam.equalsIgnoreCase(name)) {
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
