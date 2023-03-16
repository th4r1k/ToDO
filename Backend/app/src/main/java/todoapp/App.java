package todoapp;

import todoapp.Controller.AlarmController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.Service.AlarmService;
import todoapp.Model.Service.AlarmObserver;
import todoapp.Utils.Persist;
import todoapp.View.Start;

import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        Persist.createTaskFile();
        Persist.createAlarmFile();

        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());
        alarmController.getAllAlarms();

        AlarmObserver alarmObserver = new AlarmObserver(AlarmService.getInstance(), "teste@zg.com");

        System.out.println("*** Bem vindo ao ToDoApp ***");
        alarmController.checkAlarms();
        alarmController.save();
        Start.menu();


    }
}
