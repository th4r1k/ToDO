package Backend.src.Utils.Menu;

import java.util.Scanner;

import Backend.src.Service.Crud;

public class DeleteAlarm {

    public static void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Qual o nome da tarefa que gostaria de apagar o alarme?");
        String alarmToDelete = input.nextLine();
        while (alarmToDelete == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Qual o nome da tarefa que gostaria de apagar o alarme?:");
            alarmToDelete = input.nextLine();
        }
        if (!Crud.verify(alarmToDelete)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {
            Crud.deleteAlarm((alarmToDelete));
            System.out.println("Alarme Apagado com sucesso");
            Start.goBack();
        }
    }
}
