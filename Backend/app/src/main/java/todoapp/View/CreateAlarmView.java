package todoapp.View;


import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmTaskDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Entity.Alarm;

public class CreateAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmTaskDAO());
        TaskController taskController = new TaskController(new TaskDAO());
        File file = new File("data/tasks.csv");
        File alarmFile = new File("data/alarms.csv");

        Scanner input = new Scanner(System.in);
        String itemToAddAlarm = InputsView.inputName(input);

        if (!taskController.taskExist(itemToAddAlarm, file)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            String alarmDate = InputsView.inputDate(input);
            String alarmTime = InputsView.inputTime(input);

            Alarm alarm = new Alarm(itemToAddAlarm, alarmDate, alarmTime);
            alarmController.createAlarm(alarm, file, alarmFile);
            System.out.println("Alarme criado com sucesso");
            Start.goBack();
        }
    }
}
