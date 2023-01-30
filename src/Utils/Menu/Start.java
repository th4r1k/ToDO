package src.Utils.Menu;

import src.Service.Crud;
import java.util.Scanner;

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

        switch (data) {

            case "1":
                CreateTodo.menu();
                break;

            case "2": {
                System.out.println("________________________________");
                Crud.read();
                goBack();
                break;
            }

            case "3":
                EditTodo.menu();
                break;

            case "4":
                DeleteTodo.menu();
                break;

            case "5":
                OrderTodo.menu();
                break;

            case "6":
                FindTodo.menu();
                break;

            case "7": {
                Crud.count();
                goBack();
                break;
            }

            case "8": {
                CreateAlarm.menu();
                break;
            }

            case "9": {
                System.out.println("________________________________");
                Crud.readAlarms();
                goBack();
                break;

            }

            case "10":
                DeleteAlarm.menu();
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
