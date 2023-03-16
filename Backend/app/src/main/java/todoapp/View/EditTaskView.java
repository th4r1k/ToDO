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
import todoapp.Utils.Regex;

public class EditTaskView {
    public static void menu() {
        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());
        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());

        taskController.printAllTasks();

        Scanner input = new Scanner(System.in);
        String nameToEdit = InputsView.inputEditTask(input);

        if (!taskController.verifyTaskExist(nameToEdit)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            Task task = taskController.getTaskByName(nameToEdit);
            taskController.removeTask(task);

            System.out.println("Qual item quer mudar?");
            System.out.println("0- Nome da tarefaa");
            System.out.println("1- Descrição ");
            System.out.println("2- Data de termino");
            System.out.println("3- Horario de termino");
            System.out.println("4- Prioridade");
            System.out.println("5- Categoria");
            System.out.println("6- Status");
            System.out.println("Codigo do campo a ser alterado:");

            String field = input.nextLine();
            while (!(Regex.isValidCommand(field, Regex.editTaskMenuRegex))) {
                System.out.println("Qual item quer mudar?");
                field = input.nextLine();
            }
            String newdata;

            switch (field) {
                case "0":
                    newdata = InputsView.inputName(input);
                    if (taskController.verifyTaskExist(newdata)) {
                        System.out.println("________________________________");
                        System.out.println("Tarefa ja existe");
                        Start.goBack();
                    }
                    task.setName(newdata);
                    editAlarmName(alarmController, nameToEdit, newdata);
                    break;
                case "1":
                    newdata = InputsView.inputDescription(input);
                    task.setDescription(newdata);
                    break;
                case "2":
                    newdata = InputsView.inputDate(input);
                    task.setEndDate(newdata);
                    break;
                case "3":
                    newdata = InputsView.inputTime(input);
                    task.setEndTime(newdata);
                    break;
                case "4":
                    newdata = InputsView.inputPriority(input);
                    task.setPriority(newdata);
                    break;
                case "5":
                    newdata = InputsView.inputCategory(input);
                    task.setCategory(newdata);
                    break;
                case "6":
                    newdata = InputsView.inputStatus(input);
                    task.setStatus(newdata);
                    break;
                default:
                    break;
            }
            taskController.addTask(task);
            taskController.save();

            System.out.println("Item atualizado com sucesso");
            Start.goBack();
        }
    }

    private static void editAlarmName(AlarmController alarmController, String nameToEdit, String newdata) {
        if (alarmController.verifyAlarmExists(nameToEdit)) {
            Alarm alarm = alarmController.getAlarmByName(nameToEdit);
            alarmController.removeAlarm(alarm);
            alarm.setName(newdata);
            alarmController.addAlarm(alarm);
            alarmController.save();
        }
    }
}
