package todoapp.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AlarmTaskService implements AlarmTaskServiceInterface {
    public void createAlarm(String name, String dateAlarm, String timeAlarm, File file, File alarmFile) {
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
        try (FileWriter pw = new FileWriter(alarmFile, true)) {

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

    public void readAlarms(File alarmFile) {

        try {
            Scanner reader = new Scanner(alarmFile);
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAlarm(String name, File alarmFile, File tempAlaramFile) {

        try {
            FileWriter pw = new FileWriter(tempAlaramFile, true);
            Scanner reader = new Scanner(alarmFile);

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
            alarmFile.delete();
            tempAlaramFile.renameTo(alarmFile);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyAlarm(String name, File alarmFile) {
        boolean found = false;
        try {
            Scanner reader = new Scanner(alarmFile);
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


    public void updateAlarm(String name, int todoField, String newData, File alarmFile, File tempAlarmFile) {
        int fieldToEdit = 0;
        if (todoField == 2) {
            fieldToEdit = 3;
        } else if (todoField == 3) {
            fieldToEdit = 4;
        } else if (todoField == 4) {
            fieldToEdit = 5;
        }
        try {
            FileWriter pw = new FileWriter(tempAlarmFile, true);
            Scanner reader = new Scanner(alarmFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] allParams = line.split(",");
                String firstParam = allParams[0];
                int lastParam = 5;
                if (!firstParam.equals(name)) {
                    pw.write(line);
                    pw.append("\n");
                } else {
                    for (int i = 0; i <= lastParam; i++) {
                        if (i == fieldToEdit) {
                            if (i == lastParam) {
                                pw.write(newData);
                            } else {
                                pw.write(newData);
                                pw.append(",");
                            }
                        } else {
                            if (i == lastParam) {
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
            alarmFile.delete();
            tempAlarmFile.renameTo(alarmFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void alarm(File alarmFile, File tempAlarmFile) {
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String timeNow = new SimpleDateFormat("HH:mm").format(date);
        try {
            Scanner reader = new Scanner(alarmFile);
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
                        deleteAlarm(todoName, alarmFile, tempAlarmFile);
                        System.out.println("********************ALARME*******************");
                        System.out.println("O alarme da tarefa: '" + todoName + "' foi removido pois ja passou a data de termino");
                        System.out.println("********************ALARME*******************");
                        break;
                    } else if (new SimpleDateFormat("dd/MM/yyyy").parse(today).after(new SimpleDateFormat("dd/MM/yyyy").parse(dateEnd))) {
                        reader.close();
                        deleteAlarm(todoName, alarmFile, tempAlarmFile);
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
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("Houve um problema ao criar o arquivo alarms.csv");
        }
    }
}
