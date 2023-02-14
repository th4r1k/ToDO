package todoapp.Utils.Menu;

import java.util.Scanner;

import todoapp.Service.Crud;

public class FindTodo {

    public static void menu() {
        Scanner input = new Scanner(System.in);
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

        String collumn = input.nextLine();
        if (collumn.equals("7")) {
            Start.menu();
        } else if (collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5") || collumn.equals("6")) {

            System.out.println("Palavra chave para a pesquisa:");
            String word = input.nextLine();
            Crud.search(collumn, word);

            Start.goBack();
        } else {
            System.out.println("Codigo invalido, tente novamente:");
            collumn = input.nextLine();
            while (!(collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5") || collumn.equals("6"))) {
                System.out.println("Codigo invalido, tente novamente:");
                collumn = input.nextLine();

            }
        }
    }
}
    

