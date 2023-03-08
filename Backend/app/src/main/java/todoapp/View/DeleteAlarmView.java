package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmTaskDAO;
import todoapp.Model.DAO.TaskDAO;

public class DeleteAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmTaskDAO());
        TaskController taskController = new TaskController(new TaskDAO());
        File file = new File("data/tasks.csv");
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

        Scanner input = new Scanner(System.in);

        String alarmToDelete = InputsView.inputAlarm(input);
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
