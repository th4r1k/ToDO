package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Service.AlarmTaskService;
import todoapp.Service.CrudTaskService;
import todoapp.Service.TaskService;

public class Start {
    public static void menu() {

        System.out.println("1 - Criar nova tarefa");
        System.out.println("2 - Ver todas tarefas");
        System.out.println("3 - Editar tarefa");
        System.out.println("4 - Deletar tarefa");
        System.out.println("5 - Ordenar por categoria");
        System.out.println("6 - Pesquisar tarefa");
        System.out.println("7 - Estados das tarefas");
        System.out.println("8 - Criar Alarme");
        System.out.println("9 - Ver Alarme");
        System.out.println("10 - Deletar Alarme");
        System.out.println("0 - Sair");

        System.out.println("Digite o codigo do comando: ");
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskService());
        AlarmController alarmController = new AlarmController(new AlarmTaskService());
        TaskController taskController = new TaskController(new TaskService());
        File file = new File("data/tasks.csv");
        File alarmFile = new File("data/alarms.csv");

        switch (data) {
            case "1":
                CreateTaskView.menu();
                break;
            case "2": {
                System.out.println("________________________________");
                crudTaskController.readAllTasks(file);
                goBack();
                break;
            }
            case "3":
                EditTaskView.menu();
                break;
            case "4":
                DeleteTaskView.menu();
                break;
            case "5":
                OrderTaskView.menu();
                break;
            case "6":
                FindTaskView.menu();
                break;
            case "7": {
                taskController.countTasks(file);
                goBack();
                break;
            }
            case "8": {
                CreateAlarmView.menu();
                break;
            }
            case "9": {
                System.out.println("________________________________");
                alarmController.readAlarms(alarmFile);
                goBack();
                break;
            }
            case "10":
                DeleteAlarmView.menu();
                break;
            case "0": {
                input.close();
                System.out.println("Volte sempre!");
                break;
            }
            default:
                System.out.println("Opcao invalida");
                menu();
        }
    }

    public static void goBack() {
        System.out.println("");
        System.out.println("________________________________");
        System.out.println("Digite 1 para voltar ao menu");
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        switch (data) {
            case "1":
                menu();
                break;
            default:
                input.close();
                break;
        }
    }
}
