package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.Service.AlarmTaskService;
import todoapp.Model.Service.TaskService;
import todoapp.Utils.Validate;

public class DeleteAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmTaskService());
        TaskController taskController = new TaskController(new TaskService());
        File file = new File("data/tasks.csv");
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

        Scanner input = new Scanner(System.in);

        String alarmToDelete = Validate.inputAlarm(input);
        if (!taskController.taskExist(alarmToDelete, file)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            alarmController.deleteAlarm(alarmToDelete, alarmFile, tempAlarmFile);
            System.out.println("Alarme Apagado com sucesso");
            Start.goBack();
        }
    }
}
