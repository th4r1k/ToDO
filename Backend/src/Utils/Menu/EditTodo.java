package Backend.src.Utils.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Backend.src.Service.Crud;

public class EditTodo {
    public static void menu() {

        Crud.read();
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Digite o nome da tarefa que quer editar");
        String nameToEdit = input.nextLine();
        while (nameToEdit == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Digite o nome da tarefa que quer editar");
            nameToEdit = input.nextLine();
        }
        if (!Crud.verify(nameToEdit)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();
            ;
        } else {
            System.out.println("Qual item quer mudar?");
            System.out.println("0- Nome da tarefaa");
            System.out.println("1- Descrição ");
            System.out.println("2- Data de termino");
            System.out.println("3- Horario de termino");
            System.out.println("4- Prioridade");
            System.out.println("5- Categoria");
            System.out.println("6- Status");
            System.out.println("Codigo do campo a ser alterado:");

            String field = input.nextLine();
            while (!(field.equals("0") || field.equals("1") || field.equals("2") || field.equals("3") || field.equals("4") || field.equals("5") || field.equals("6"))) {
                System.out.println("Qual item quer mudar?");
                field = input.nextLine();
            }
            String newdata;

            if (field.equals("0")) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                if (Crud.verify(newdata)) {
                    System.out.println("________________________________");
                    System.out.println("Tarefa ja existe");
                    Start.goBack();
                }

            } else if (field.equals("2")) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();

                String regex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})" ;
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(newdata);
                boolean dateOk = matcher.matches();

                while (!dateOk) {
                    System.out.println("Data precisar ser no formato dd/mm/aaaa");
                    newdata = input.nextLine();
                    matcher = pattern.matcher(newdata);
                    dateOk = matcher.matches();
                }

            } else if (field.equals("3")) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                String regexTime = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$" ;
                Pattern patternTime = Pattern.compile(regexTime);
                Matcher matcherTime = patternTime.matcher(newdata);
                boolean timeOk = matcherTime.matches();

                while (!timeOk) {
                    System.out.println("Horario precisar ser no formato (ex.: 23:59)");
                    newdata = input.nextLine();
                    matcherTime = patternTime.matcher(newdata);
                    timeOk = matcherTime.matches();
                }
            } else if (field.equals("4")) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                while (!(newdata.equals("1") || newdata.equals("2") || newdata.equals("3") || newdata.equals("4") || newdata.equals("5"))) {
                    System.out.println("Prioridade precisa ser um NUMERO de 1 a 5");
                    System.out.println("Qual o nivel de prioridade?");
                    newdata = input.nextLine();
                }
            } else if (field.equals("6")) {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine().toLowerCase();
                while (!(newdata.equals("todo") || newdata.equals("doing") || newdata.equals("done"))) {
                    System.out.println("Qual o Status (ToDo,Doing,Done):");
                    newdata = input.nextLine().toLowerCase();
                }
            } else {
                System.out.println("Insira o novo valor:");
                newdata = input.nextLine();
                while (newdata == "") {
                    System.out.println("Campo nao pode ficar vazio");
                    System.out.println("Insira o novo valor:");
                    newdata = input.nextLine().toLowerCase();
                }
            }

            Crud.update(nameToEdit, Integer.parseInt(field), newdata);
            if (Crud.verifyAlarm(nameToEdit)) {
                if (field.equals("0") || field.equals("2") || field.equals("3") || field.equals("4") || field.equals("5")) {
                    Crud.updateAlarm(nameToEdit, Integer.parseInt(field), newdata);
                }
            }
            System.out.println("Item atualizado com sucesso");
            Start.goBack();
        }
    }

}
