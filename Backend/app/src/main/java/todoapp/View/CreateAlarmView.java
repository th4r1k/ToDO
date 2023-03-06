package todoapp.View;


import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Service.AlarmTaskService;
import todoapp.Service.TaskService;
import todoapp.Utils.Regex;
import todoapp.Utils.Validate;

public class CreateAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmTaskService());
        TaskController taskController = new TaskController(new TaskService());
        File file = new File("data/tasks.csv");
        File alarmFile = new File("data/alarms.csv");

        Scanner input = new Scanner(System.in);
        String itemToAddAlarm = Validate.inputName(input);

        if (!taskController.taskExist(itemToAddAlarm, file)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            String alarmDate = Validate.inputDate(input);
            String alarmTime = Validate.inputTime(input);

            alarmController.createAlarm(itemToAddAlarm, alarmDate, alarmTime, file, alarmFile);
            System.out.println("Alarme criado com sucesso");
            Start.goBack();
        }
    }
}
