package todoapp.Model.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskService implements TaskServiceInterface{

    public boolean verify(String name, File file) {

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


    public List<List<String>> dataToArray(File file) {
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


    public void sorter(String fieldToSort, File file, File tempfile) {
        List<List<String>> items = dataToArray(file);
        items.remove(0);
        Collections.sort(items, new Comparator<List<String>>() {
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

            @Override
            public int compare(List<String> list1, List<String> list2) {
                if ((!fieldToSort.equals("2"))) {
                    return list1.get(Integer.parseInt(fieldToSort)).toLowerCase().compareTo(list2.get(Integer.parseInt(fieldToSort)).toLowerCase());
                } else {
                    try {
                        return dateFormat.parse(list1.get(Integer.parseInt(fieldToSort))).compareTo(dateFormat.parse(list2.get(Integer.parseInt(fieldToSort))));
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


    public void search(String fieldToSearch, String name, File file) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String firstParam = line.split(",")[Integer.parseInt(fieldToSearch)];
                if (firstParam.contains(name)) {
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void count(File file) {
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
}
