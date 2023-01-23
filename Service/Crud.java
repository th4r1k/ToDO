package Service;

import Entity.Todo;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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

    public static boolean verify(String name ) {

        File file = new File("data/todos.csv");
        boolean found = false;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[0];
                if (firstParam.contentEquals(name)) {
                    found= true;
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    public static List<List<String>> dataToArray(){
        File file = new File("data/todos.csv");
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

    public static void sorter(String coluna){
        File file = new File("data/todos.csv");
        File tempfile = new File("data/temptodos.csv");

        List<List<String>> items = dataToArray();
        items.remove(0);
        Collections.sort(items, new Comparator<List<String>>() {
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            @Override
            public int compare(List<String> list1, List<String> list2) {
                if ((!coluna.equals("2"))){
                return list1.get(Integer.parseInt(coluna)).toLowerCase().compareTo(list2.get(Integer.parseInt(coluna)).toLowerCase());
            }
                else {
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
            pw.write("Name" + "," + "Description" + "," + "EndDate" + "," + "Priority" + "," + "Category" + "," + "Status");
            pw.append("\n");

            for(int i=0 ; i<items.size() ; i++){
                for (int j=0 ; j<items.get(i).size() ; j++){
                    pw.write(items.get(i).get(j));
                    if(j!= items.get(i).size()-1)
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

        File file = new File("data/todos.csv");

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

}
