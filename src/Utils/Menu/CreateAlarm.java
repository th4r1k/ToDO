package src.Utils.Menu;


import src.Service.Crud;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAlarm {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome da tarefa que quer criar um alarme");
        String itemToAddAlarm = input.nextLine();
        while (itemToAddAlarm == "") {
            System.out.println("Campo nao pode ficar vazio");
            System.out.println("Nome da tarefa para criar alarme:");
            itemToAddAlarm = input.nextLine();
//                    goBack();
        }
        if (!Crud.verify(itemToAddAlarm)) {
            System.out.println("Tarefa nao encontrada");
            Start.goBack();

        } else {

            System.out.println("Qual a data para o alarme(dd/mm/aaaa)? ");
            String alarmDate = input.nextLine();
            String regex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})" ;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(alarmDate);
            boolean dateOk = matcher.matches();

            while (!dateOk) {
                System.out.println("Data precisar ser no formato dd/mm/aaaa");
                alarmDate = input.nextLine();
                matcher = pattern.matcher(alarmDate);
                dateOk = matcher.matches();
            }


            System.out.println("Qual o horario para o alarme(ex.: 23:59) ");
            String alarmTime = input.nextLine();
            String regexTime = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$" ;
            Pattern patternTime = Pattern.compile(regexTime);
            Matcher matcherTime = patternTime.matcher(alarmTime);
            boolean timeOk = matcherTime.matches();

            while (!timeOk) {
                System.out.println("Horario precisar ser no formato (ex.: 23:59)");
                alarmTime = input.nextLine();
                matcherTime = patternTime.matcher(alarmTime);
                timeOk = matcherTime.matches();
            }

            Crud.createAlarm(itemToAddAlarm, alarmDate, alarmTime);
            System.out.println("Alarme criado com sucesso");
            Start.goBack();
        }
    }
}
