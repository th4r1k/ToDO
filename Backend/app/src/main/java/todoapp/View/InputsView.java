package todoapp.View;

import todoapp.Utils.Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputsView {

   public static String inputName(Scanner input){
        System.out.println("Digite o nome da tarefa");
        String name = input.nextLine();
        while (name == "") {
            System.out.println("Nome nao pode ficar em branco");
            System.out.println("Digite o nome da tarefa");
            name = input.nextLine();
        }
        return name;
    }

    public static String inputDescription(Scanner input){
        System.out.println("Descreva a tarefa");
        String description = input.nextLine();
        while (description == "") {
            System.out.println("Descricao nao pode ficar em branco");
            System.out.println("Descreve a tarefa");
            description = input.nextLine();
        }
        return description;
    }

    public static String inputDate(Scanner input){
        System.out.println("Data para termino da tarefa (dia/mes/ano)");
        String endDate = input.nextLine();
        Pattern pattern = Pattern.compile(Regex.dateRegex);
        Matcher matcher = pattern.matcher(endDate);
        boolean dateOk = matcher.matches();
        while (!dateOk) {
            System.out.println("Data precisar ser no formato dd/mm/aaaa");
            endDate = input.nextLine();
            matcher = pattern.matcher(endDate);
            dateOk = matcher.matches();
        }
        return endDate;
    }

    public static String inputTime(Scanner input){
        System.out.println("Horario para terminar da tarefa (formato ex.: 23:59) ");
        String endTime = input.nextLine();
        Pattern patternTime = Pattern.compile(Regex.timeRegex);
        Matcher matcherTime = patternTime.matcher(endTime);
        boolean timeOk = matcherTime.matches();
        while (!timeOk) {
            System.out.println("Horario precisar ser no formato (ex.: 23:59)");
            endTime = input.nextLine();
            matcherTime = patternTime.matcher(endTime);
            timeOk = matcherTime.matches();
        }
        return endTime;
    }

    public static String inputPriority(Scanner input){
        System.out.println("Nivel de prioridade (1-5)");
        String priority = input.nextLine();
        while (!(Regex.isValidCommand(priority, Regex.priority))) {
            System.out.println("Prioridade precisa ser um NUMERO de 1 a 5");
            System.out.println("Qual o nivel de prioridade?");
            priority = input.nextLine();
        }
        return priority;
    }

    public static String inputCategory(Scanner input){
        System.out.println("Categoria da tarefa");
        String category = input.nextLine();
        while (category == "") {
            System.out.println("Categoria nao pode ficar em branco");
            System.out.println("Digite o nome da categoria");
            category = input.nextLine();
        }
        return category;
    }

    public static String inputStatus(Scanner input){
        System.out.println("Qual o Status (ToDo,Doing,Done)");
        String status = input.nextLine().toLowerCase();
        while (!(status.equals("todo") || status.equals("doing") || status.equals("done"))) {
            System.out.println("Qual o Status (ToDo,Doing,Done)");
            status = input.nextLine().toLowerCase();
        }
        return status;
    }

    public static String inputAlarm(Scanner input){
        System.out.println("Qual o nome da tarefa que gostaria de apagar o alarme?");
        String alarmToDelete = input.nextLine();
        while (alarmToDelete == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Qual o nome da tarefa que gostaria de apagar o alarme?:");
            alarmToDelete = input.nextLine();
        }
        return alarmToDelete;
    }

    public static String inputDeleteTask(Scanner input){
        System.out.println("Qual o nome da tarefa que gostaria de apagar?");
        String itemToDelete = input.nextLine();
        while (itemToDelete == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Qual o nome da tarefa que gostaria de apagar?:");
            itemToDelete = input.nextLine();

        }
        return itemToDelete;
    }

    public static String inputEditTask(Scanner input){
        System.out.println("");
        System.out.println("Digite o nome da tarefa que quer editar");
        String nameToEdit = input.nextLine();
        while (nameToEdit == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Digite o nome da tarefa que quer editar");
            nameToEdit = input.nextLine();
        }
        return nameToEdit;
    }

}
