package todoapp.Utils.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import todoapp.Entity.Todo;
import todoapp.Service.Crud;

public class CreateTodo {

    public static void menu() {

        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome da tarefa");
        String name = input.nextLine();
        while (name == "") {
            System.out.println("Nome nao pode ficar em branco");
            System.out.println("Digite o nome da tarefa");
            name = input.nextLine();
        }
        if (Crud.verify(name)) {
            System.out.println("________________________________");
            System.out.println("Tarefa ja existe");
            Start.goBack();
        } else {
            System.out.println("Descreva a tarefa");
            String description = input.nextLine();
            while (description == "") {
                System.out.println("Descricao nao pode ficar em branco");
                System.out.println("Descreve a tarefa");
                description = input.nextLine();
            }

            System.out.println("Data para termino da tarefa (dia/mes/ano)");
            String endDate = input.nextLine();
            String regex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})" ;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(endDate);
            boolean dateOk = matcher.matches();

            while (!dateOk) {
                System.out.println("Data precisar ser no formato dd/mm/aaaa");
                endDate = input.nextLine();
                matcher = pattern.matcher(endDate);
                dateOk = matcher.matches();
            }

            System.out.println("Horario para terminar da tarefa (formato ex.: 23:59) ");
            String endTime = input.nextLine();
            String regexTime = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$" ;
            Pattern patternTime = Pattern.compile(regexTime);
            Matcher matcherTime = patternTime.matcher(endTime);
            boolean timeOk = matcherTime.matches();

            while (!timeOk) {
                System.out.println("Horario precisar ser no formato (ex.: 23:59)");
                endTime = input.nextLine();
                matcherTime = patternTime.matcher(endTime);
                timeOk = matcherTime.matches();
            }

            System.out.println("Nivel de prioridade (1-5)");
            String priority = input.nextLine();
            while (!(priority.equals("1") || priority.equals("2") || priority.equals("3") || priority.equals("4") || priority.equals("5"))) {
                System.out.println("Prioridade precisa ser um NUMERO de 1 a 5");
                System.out.println("Qual o nivel de prioridade?");
                priority = input.nextLine();
            }

            System.out.println("Categoria da tarefa");
            String category = input.nextLine();
            while (category == "") {
                System.out.println("Categoria nao pode ficar em branco");
                System.out.println("Digite o nome da categoria");
                category = input.nextLine();
            }

            System.out.println("Qual o Status (ToDo,Doing,Done)");
            String status = input.nextLine().toLowerCase();
            while (!(status.equals("todo") || status.equals("doing") || status.equals("done"))) {
                System.out.println("Qual o Status (ToDo,Doing,Done)");
                status = input.nextLine().toLowerCase();
            }

            Todo todo = new Todo(name, description, endDate, endTime, priority, category, status);
            Crud.create(todo);
            Crud.sorter("4");

            System.out.println("________________________________");
            System.out.println("Tarefa cadastrada com sucesso!");
            Start.menu();
        }
    }

}
