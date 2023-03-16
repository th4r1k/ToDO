package todoapp.Model.DAO;

import todoapp.Model.Entity.Alarm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AlarmDAO implements AlarmDAOInterface {
    final private File alarmFile = new File("data/alarms.csv");

    public void save(List<Alarm> alarmList) {
        alarmFile.delete();
        try (FileWriter pw = new FileWriter(alarmFile, true)) {

                pw.write("ToDo`s Name" + "," + "AlarmDate" + "," + "AlarmTime");
                pw.append("\n");

            for (Alarm alarm : alarmList) {
                pw.write(alarm.toString());
                pw.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarmList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(alarmFile);
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] array = line.split((","));
                Alarm alarm = new Alarm(array[0], array[1], array[2]);
                alarmList.add(alarm);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel ler o arquivo de tarefas e converter para arrays");
        }
        return alarmList;
    }

}
