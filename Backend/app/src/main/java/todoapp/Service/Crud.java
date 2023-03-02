package todoapp.Service;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import todoapp.Entity.Task;

public class Crud {

    public static void create(Task task) {

        File file = new File("data/tasks.csv");
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

    public static void read() {
        File file = new File("data/tasks.csv");
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
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");
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


    public static void delete(String name) {

        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");
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


    public static boolean verify(String name) {

        File file = new File("data/tasks.csv");
        boolean found = false;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (firstParam.contentEquals(name)) {
                    found = true;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return found;
    }


    public static List<List<String>> dataToArray() {
        File file = new File("data/tasks.csv");
        List<List<String>> items = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                items.add(Arrays.asList(line.split(",")));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return items;
    }


    public static void sorter(String coluna) {
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");

        List<List<String>> items = dataToArray();
        items.remove(0);
        Collections.sort(items, new Comparator<List<String>>() {
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

            @Override
            public int compare(List<String> list1, List<String> list2) {
                if ((!coluna.equals("2"))) {
                    return list1.get(Integer.parseInt(coluna)).toLowerCase().compareTo(list2.get(Integer.parseInt(coluna)).toLowerCase());
                } else {
                    try {
                        return dateFormat.parse(list1.get(Integer.parseInt(coluna))).compareTo(dateFormat.parse(list2.get(Integer.parseInt(coluna))));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });
        try {
            FileWriter pw = new FileWriter(tempfile, true);
            pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
            pw.append("\n");

            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < items.get(i).size(); j++) {
                    pw.write(items.get(i).get(j));
                    if (j != items.get(i).size() - 1)
                        pw.write(",");
                }
                pw.append("\n");
            }
            pw.close();
            file.delete();
            tempfile.renameTo(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void search(String collumn, String name) {

        File file = new File("data/tasks.csv");

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[Integer.parseInt(collumn)];
                if (firstParam.contains(name)) {
                    System.out.println(line);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void count() {
        File file = new File("data/tasks.csv");
        int todo = 0;
        int doing = 0;
        int done = 0;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[6];
                if (firstParam.equals("todo")) {
                    todo++;
                } else if (firstParam.equals("doing")) {
                    doing++;
                } else if (firstParam.equals("done")) {
                    done++;
                }
            }
            System.out.println("itens a fazer(ToDo): " + todo);
            System.out.println("itens em andamento(Doing): " + doing);
            System.out.println("itens concluidos(Done): " + done);
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createAlarm(String name, String dateAlarm, String timeAlarm) {

        File file = new File("data/tasks.csv");
        String endDate = "" ;
        String endTime = "" ;
        String priority = "" ;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (firstParam.contentEquals(name)) {
                    endDate = line.split(",")[2];
                    endTime = line.split(",")[3];
                    priority = line.split(",")[4];
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File alarmFile = new File("data/alarms.csv");
        try (FileWriter pw = new FileWriter(alarmFile, true)) {


            if (alarmFile.length() == 0) {
                pw.write("ToDo`s Name" + "," + "AlarmDate" + "," + "AlarmTime" + "," + "EndDate" + "," + "EndTime" + "," + "Priority");
                pw.append("\n");
            }
            if (!(endDate.equals("")))
                pw.write(name + ",");
            pw.write(dateAlarm + ",");
            pw.write(timeAlarm + ",");
            pw.write(endDate + ",");
            pw.write(endTime + ",");
            pw.write(priority);
            pw.append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void readAlarms() {
        File file = new File("data/alarms.csv");
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


    public static void deleteAlarm(String name) {

        File file = new File("data/alarms.csv");
        File tempfile = new File("data/tempalarms.csv");
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


    public static boolean verifyAlarm(String name) {

        File file = new File("data/alarms.csv");
        boolean found = false;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (firstParam.contentEquals(name)) {
                    found = true;
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return found;
    }


    public static void updateAlarm(String name, int todoField, String newData) {
        File file = new File("data/alarms.csv");
        File tempfile = new File("data/tempalarms.csv");
        int fieldToEdit = 0;

        if (todoField == 2) {
            fieldToEdit = 3;
        } else if (todoField == 3) {
            fieldToEdit = 4;
        } else if (todoField == 4) {
            fieldToEdit = 5;
        }

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

                        if (i == fieldToEdit) {
                            if (i == 5) {
                                pw.write(newData);

                            } else {
                                pw.write(newData);
                                pw.append(",");
                            }
                        } else {
                            if (i == 5) {
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


    public static void alarm() {

        File file = new File("data/alarms.csv");
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String timeNow = new SimpleDateFormat("HH:mm").format(date);

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!(line.equals("ToDo`s Name,AlarmDate,AlarmTime,EndDate,EndTime,Priority"))) {
                    String todoName = line.split(",")[0];
                    String dateAlarm = line.split(",")[1];
                    String timeAlarm = line.split(",")[2];
                    String dateEnd = line.split(",")[3];
                    String timeEnd = line.split(",")[4];

                    if (dateEnd.equals(today) & new SimpleDateFormat("HH:mm").parse(timeNow).after(new SimpleDateFormat("HH:mm").parse(timeEnd))) {
                        reader.close();
                        deleteAlarm((todoName));
                        System.out.println("********************ALARME*******************");
                        System.out.println("O alarme da tarefa: '" + todoName + "' foi removido pois ja passou a data de termino");
                        System.out.println("********************ALARME*******************");
                        break;
                    } else if (new SimpleDateFormat("dd/MM/yyyy").parse(today).after(new SimpleDateFormat("dd/MM/yyyy").parse(dateEnd))) {
                        reader.close();
                        deleteAlarm((todoName));
                        System.out.println("********************ALARME*******************");
                        System.out.println("O alarme da tarefa: '" + todoName + "' foi removido pois ja passou a data de termino");
                        System.out.println("********************ALARME*******************");
                        break;

                    } else if (dateAlarm.equals(today) & new SimpleDateFormat("HH:mm").parse(timeNow).after(new SimpleDateFormat("HH:mm").parse(timeAlarm))) {
                        System.out.println("********************ALARME*******************");
                        System.out.println("A tarefa:" + " " + line.split(",")[0] + ", " + "Data de termino" + " " + line.split(",")[3] + " " + "as: " + line.split(",")[4]);
                        System.out.println("********************ALARME*******************");

                    }
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Houve um problema ao criar o arquivo alarms.csv");
            // throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("Houve um problema ao criar o arquivo alarms.csv");
            // throw new RuntimeException(e);
        }
    }
}
