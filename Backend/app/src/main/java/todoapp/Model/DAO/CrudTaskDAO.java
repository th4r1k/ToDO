package todoapp.Model.DAO;

import java.io.*;
import java.util.*;


import todoapp.Model.Entity.Task;

public class CrudTaskDAO implements CrudTaskDAOInterface {

    public boolean create(Task task, File file) {
        boolean isOk = false;

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
            isOk = true;

        } catch (IOException e) {
            System.out.println("Nao foi possivel criar a tarefa");
        }
        return isOk;
    }

    public boolean read(File file) {
        boolean isOk = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
            isOk = true;
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel ler a tarefa");
        }
        return isOk;
    }

    public boolean update(String name, int field, String newData, File file, File tempfile) {
        boolean isOk= false;
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
            isOk=true;
        } catch (IOException e) {
            System.out.println("Nao foi possivel editar a tarefa");        }
        return isOk;
    }

    public boolean delete(String name, File file, File tempfile) {
        boolean isOk= false;
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
            isOk = true;
        } catch (IOException e) {
            System.out.println("Nao foi possivel deletar a tarefa");
        }
        return isOk;
    }
}
