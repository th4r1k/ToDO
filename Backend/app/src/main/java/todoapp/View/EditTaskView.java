package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmTaskDAO;
import todoapp.Model.DAO.CrudTaskDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Utils.Regex;

public class EditTaskView {
    public static void menu() {
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskDAO());
        AlarmController alarmController = new AlarmController(new AlarmTaskDAO());
        TaskController taskController = new TaskController(new TaskDAO());
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");
        File alarmFile = new File("data/alarms.csv");
        File tempAlarmFile = new File("data/tempalarms.csv");
        crudTaskController.readAllTasks(file);

        Scanner input = new Scanner(System.in);
        String nameToEdit = InputsView.inputEditTask(input);

        if (!taskController.taskExist(nameToEdit, file)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
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
            String nameOption = "0";
            String endDateOption = "2";
            String endTimeOption = "3";
            String priorityOption = "4";
            String statusOption = "6";

            if (field.equals(nameOption)) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                if (taskController.taskExist(newdata, file)) {
                    System.out.println("________________________________");
                    System.out.println("Tarefa ja existe");
                    Start.goBack();
                }
            } else if (field.equals(endDateOption)) {
                newdata = InputsView.inputDate(input);
            } else if (field.equals(endTimeOption)) {
                newdata = InputsView.inputTime(input);
            } else if (field.equals(priorityOption)) {
                newdata = InputsView.inputPriority(input);
            } else if (field.equals(statusOption)) {
                newdata = InputsView.inputStatus(input);
            } else {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                while (newdata == "") {
                    System.out.println("Campo nao pode ficar vazio");
                    System.out.println("Insira o novo valor:");
                    newdata = input.nextLine().toLowerCase();
                }
            }
            crudTaskController.updateTask(nameToEdit, Integer.parseInt(field), newdata, file, tempfile);
            if (alarmController.verifyAlarm(nameToEdit, alarmFile)) {
                if (Regex.isValidCommand(field, "[02345]")) {
                    alarmController.updateAlarm(nameToEdit, Integer.parseInt(field), newdata, alarmFile, tempAlarmFile);
                }
            }
            System.out.println("Item atualizado com sucesso");
            Start.goBack();
        }
    }


}
