package todoapp;

import todoapp.Controller.AlarmController;
import todoapp.Model.DAO.AlarmTaskDAO;
import todoapp.View.Start;
import todoapp.Utils.Persist;

import java.io.File;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        AlarmController alarmController = new AlarmController(new AlarmTaskDAO());
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

        Persist.createTaskFile();
        Persist.createAlarmFile();

        System.out.println("*** Bem vindo ao ToDoApp ***");
        alarmController.startAlarm(alarmFile, tempAlarmFile);
        Start.menu();

    }
}
