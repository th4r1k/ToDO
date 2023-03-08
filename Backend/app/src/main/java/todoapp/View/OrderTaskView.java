package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Model.DAO.CrudTaskDAO;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Utils.Regex;

import static todoapp.Utils.Regex.isValidCommand;

public class OrderTaskView {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskDAO());
        TaskController taskController = new TaskController(new TaskDAO());
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");

        System.out.println("Organizar por:");
        System.out.println("0- Nome da tarefa");
        System.out.println("1- Descricao");
        System.out.println("2- Data de termino");
        System.out.println("3- Hora de termino");
        System.out.println("4- Prioridade");
        System.out.println("5- Categoria");
        System.out.println("6- Status");
        System.out.println("7- Voltar");
        System.out.println("Digite o codigo:");

        String fieldToSort = input.nextLine();
        String returnToMenu = "7";

        if (fieldToSort.equals(returnToMenu)) {
            Start.menu();
        } else if (isValidCommand(fieldToSort, Regex.orderTaskMenuRegex)) {
            taskController.sortTask(fieldToSort, file, tempfile);
            System.out.println("Lista reordenada com sucesso");
            crudTaskController.readAllTasks(file);
            Start.goBack();
        } else {
            System.out.println("Codigo invalido, tente novamente:");
            fieldToSort = input.nextLine();
            while (!(isValidCommand(fieldToSort, Regex.orderTaskMenuRegex))) {
                System.out.println("Codigo invalido, tente novamente:");
                fieldToSort = input.nextLine();
            }
        }
    }

}
