package todoapp.View;


import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Model.Service.AlarmTaskService;
import todoapp.Model.Service.CrudTaskService;
import todoapp.Model.Service.TaskService;
import todoapp.Utils.Validate;

public class DeleteTaskView {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskService());
        AlarmController alarmController = new AlarmController(new AlarmTaskService());
        TaskController taskController = new TaskController(new TaskService());
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");

       String itemToDelete = Validate.inputDeleteTask(input);
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
