package todoapp.View;

import java.util.Scanner;
import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Entity.Alarm;
import todoapp.Model.Entity.Task;
import todoapp.Model.Service.AlarmService;
import todoapp.Model.Service.TaskService;

public class DeleteTaskView {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());
        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());

       String taskNameToDelete = InputsView.inputDeleteTask(input);
        if (!taskController.verifyTaskExist(taskNameToDelete)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            Task taskToDelete = taskController.getTaskByName(taskNameToDelete);
            taskController.removeTask(taskToDelete);
            taskController.save();
            Alarm alarmToDelete = alarmController.getAlarmByName(taskNameToDelete);
            alarmController.removeAlarm(alarmToDelete);
            alarmController.save();

            System.out.println("Item Apagado com sucesso");
            Start.goBack();
        }
    }
}
