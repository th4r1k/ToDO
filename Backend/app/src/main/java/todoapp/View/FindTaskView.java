package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.TaskController;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Service.TaskService;
import todoapp.Utils.Regex;

public class FindTaskView {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());

        System.out.println("Pesquisar por:");
        System.out.println("0- Nome da tarefa");
        System.out.println("1- Descricao");
        System.out.println("2- Data de termino");
        System.out.println("3- Horario termino");
        System.out.println("4- Prioridade");
        System.out.println("5- Categoria");
        System.out.println("6- Status");
        System.out.println("7- Voltar");
        System.out.println("Digite o codigo:");

        String fieldToSearch = input.nextLine();
        String returnToMenu = "7";
        if (fieldToSearch.equals(returnToMenu)) {
            Start.showMenu();
        } else if (Regex.isValidCommand(fieldToSearch, Regex.findTaskMenuRegex)) {

            System.out.println("Palavra chave para a pesquisa:");
            String name = input.nextLine();
            taskController.search(fieldToSearch, name);

            Start.goBack();
        } else {
            System.out.println("Codigo invalido, tente novamente:");
            fieldToSearch = input.nextLine();
            while (!(Regex.isValidCommand(fieldToSearch, Regex.findTaskMenuRegex))) {
                System.out.println("Codigo invalido, tente novamente:");
                fieldToSearch = input.nextLine();

            }
        }
    }
}
    

