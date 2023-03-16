package todoapp.View;

import java.util.Scanner;

import todoapp.Controller.AlarmController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Service.AlarmService;
import todoapp.Model.Service.TaskService;

public class Start {
    public static void menu() {
        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());
        AlarmController alarmController = new AlarmController(new AlarmDAO(), AlarmService.getInstance());
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        taskController.getAllTasks();
        alarmController.getAllAlarms();

        showMenu();
        while (!quit) {
            String command = input.nextLine();

            switch (command) {
                case "1":
                    CreateTaskView.menu();
                    break;
                case "2": {
                    System.out.println("________________________________");
                    System.out.println("Name" + "," + "Description" + "," + "EndDate" + "," + "EndTime" + "," + "Priority" + "," + "Category" + "," + "Status");
                    taskController.printAllTasks();
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
                    taskController.countTasks();
                    goBack();
                    break;
                }
                case "8": {
                    CreateAlarmView.menu();
                    break;
                }
                case "9": {
                    System.out.println("________________________________");
                    System.out.println("ToDo`s Name" + "," + "AlarmDate" + "," + "AlarmTime");
                    alarmController.printAllAlarms();
                    goBack();
                    break;
                }
                case "10":
                    DeleteAlarmView.menu();
                    break;
                case "0": {
                    quit = true;
                    input.close();
                    System.out.println("Volte sempre!");
                    break;
                }
                default:
                    System.out.println("Opcao invalida");
                    menu();
            }
        }
    }

    public static void showMenu() {
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
    }

    public static void goBack() {
        System.out.println("");
        System.out.println("________________________________");
        System.out.println("Digite 1 para voltar ao menu");
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        switch (command) {
            case "1":
                showMenu();
                break;
            default:
                input.close();
                break;
        }
    }
}
