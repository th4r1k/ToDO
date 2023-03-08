package todoapp.View;


import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmTaskDAO;
import todoapp.Model.DAO.CrudTaskDAO;
import todoapp.Model.DAO.TaskDAO;

public class DeleteTaskView {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskDAO());
        AlarmController alarmController = new AlarmController(new AlarmTaskDAO());
        TaskController taskController = new TaskController(new TaskDAO());
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

       String itemToDelete = InputsView.inputDeleteTask(input);
        if (!taskController.taskExist(itemToDelete, file)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            crudTaskController.deleteTask(itemToDelete, file, tempfile);
            alarmController.deleteAlarm(itemToDelete, alarmFile, tempAlarmFile);
            System.out.println("Item Apagado com sucesso");
            Start.goBack();
        }
    }
}
