package todoapp.View;


import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.Entity.Alarm;
import todoapp.Model.Service.AlarmService;
import todoapp.Model.Service.TaskService;

public class CreateAlarmView {

    public static void menu() {
        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());
        TaskService taskService = TaskService.getInstance();
        alarmController.getAllAlarms();

        Scanner input = new Scanner(System.in);
        String itemToAddAlarm = InputsView.inputName(input);

        if (!taskService.verifyExists(itemToAddAlarm)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            String alarmDate = InputsView.inputAlarmDate(input);
            String alarmTime = InputsView.inputAlarmTime(input);

            Alarm alarm = new Alarm(itemToAddAlarm, alarmDate, alarmTime);
            alarmController.addAlarm(alarm);
            alarmController.save();

            System.out.println("Alarme criado com sucesso");
            Start.goBack();
        }
    }
}
