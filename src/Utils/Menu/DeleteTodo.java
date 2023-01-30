package src.Utils.Menu;


import src.Service.Crud;
import java.util.Scanner;
public class DeleteTodo {

    public static void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Qual o nome da tarefa que gostaria de apagar?");
        String itemToDelete = input.nextLine();
        while (itemToDelete == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Qual o nome da tarefa que gostaria de apagar?:");
            itemToDelete = input.nextLine();

        }
        if (!Crud.verify(itemToDelete)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            Crud.delete((itemToDelete));
            Crud.deleteAlarm((itemToDelete));
            System.out.println("Item Apagado com sucesso");
            Start.goBack();
        }
    }
}
