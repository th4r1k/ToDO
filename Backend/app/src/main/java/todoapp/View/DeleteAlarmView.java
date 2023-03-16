package todoapp.View;

import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Entity.Alarm;
import todoapp.Model.Service.AlarmService;
import todoapp.Model.Service.TaskService;

public class DeleteAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());
        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());

        Scanner input = new Scanner(System.in);

        String alarmToDelete = InputsView.inputAlarm(input);
        if (!taskController.verifyTaskExist(alarmToDelete)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            Alarm alarm = alarmController.getAlarmByName(alarmToDelete);
            alarmController.removeAlarm(alarm);
            alarmController.save();

            System.out.println("Alarme Apagado com sucesso");
            Start.goBack();
        }
    }
}
