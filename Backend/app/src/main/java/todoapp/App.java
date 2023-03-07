package todoapp;

import todoapp.Controller.AlarmController;
import todoapp.Model.Service.AlarmTaskService;
import todoapp.View.Start;
import todoapp.Utils.Persist;

import java.io.File;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        AlarmController alarmController = new AlarmController(new AlarmTaskService());
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

        Persist.createFiles();

        System.out.println("*** Bem vindo ao ToDoApp ***");
        alarmController.startAlarm(alarmFile, tempAlarmFile);
        Start.menu();

    }
}
