package utils;

import Entity.Todo;
import Service.Crud;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Start {
    public static void menu() {

        System.out.println("1 - Criar nova tarefa");
        System.out.println("2 - Ver todas tarefas");
        System.out.println("3 - Editar tarefa");
        System.out.println("4 - Deletar tarefa");
        System.out.println("5 - Ordenar por categoria");
        System.out.println("6 - Pesquisar tarefa");
        System.out.println("7 - Estados das tarefas");
        System.out.println("0 - Sair");

        System.out.println("Digite o codigo do comando: ");
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        switch (data) {

            case "1":
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
                    goBack();
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
                    String regex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(endDate);
                    boolean dateOk = matcher.matches();

                    while (!dateOk) {
                        System.out.println("Data precisar ser no normato dd/mm/aaaa");
                        endDate = input.nextLine();
                        matcher = pattern.matcher(endDate);
                        dateOk = matcher.matches();
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
                    String status = input.nextLine();
                    while (!(status.equals("todo") || status.equals("doing") || status.equals("done"))) {
                        System.out.println("Status nao pode ficar em branco, precisa ser(todo|doing|done)");
                        System.out.println("Qual o Status (ToDo,Doing,Done)");
                        status = input.nextLine().toLowerCase();
                    }

                    Todo todo = new Todo(name, description, endDate, priority, category, status);
                    Crud.create(todo);
                    Crud.sorter("3");
                    System.out.println("________________________________");
                    System.out.println("Tarefa cadastrada com sucesso!");
                    menu();
                }
                break;

            case "2": {
                System.out.println("________________________________");

                Crud.read();
                goBack();

                break;
            }

            case "3":
                Crud.read();
                System.out.println("Digite o nome da tarefa que quer editar");
                String nameToEdit = input.nextLine();
                while (nameToEdit == "") {
                    System.out.println("Campo nao pode ficar vazio");
                    System.out.println("Digite o nome da tarefa que quer editar");
                    nameToEdit = input.nextLine();
                }
                if (!Crud.verify(nameToEdit)) {
                    System.out.println("Tarefa nao encontrada");
                    goBack();
                } else {
                    System.out.println("Qual item quer mudar?");
                    System.out.println("0- Nome da tarefaa");
                    System.out.println("1- Descrição ");
                    System.out.println("2- Data de termino");
                    System.out.println("3- Prioridade");
                    System.out.println("4- Categoria");
                    System.out.println("5- Status");
                    System.out.println("Codigo do campo a ser alterado:");

                    String field = input.nextLine();
                    while (!(field.equals("0") || field.equals("1") || field.equals("2") || field.equals("3") || field.equals("4") || field.equals("5"))) {
                        System.out.println("Qual item quer mudar?");
                        field = input.nextLine();
                    }

                    System.out.println("Insira o novo valor:");
                    String newdata = input.nextLine();
                    while (newdata == "") {
                        System.out.println("Campo nao pode ficar vazio");
                        System.out.println("Insira o novo valor:");
                        newdata = input.nextLine().toLowerCase();
                    }

                    Crud.update(nameToEdit, Integer.parseInt(field), newdata);
                    System.out.println("Item atualizado com sucesso");
                    goBack();
                }

                break;

            case "4":

                System.out.println("Qual o nome da tarefa que gostaria de apagar?");
                String itemToDelete = input.nextLine();
                while (itemToDelete == "") {
                    System.out.println("Campo nao pode ficar vazio");
                    System.out.println("Insira o novo valor:");
                    itemToDelete = input.nextLine();
//                    goBack();
                }
                if (!Crud.verify(itemToDelete)) {
                    System.out.println("Tarefa nao encontrada");
                    goBack();

                } else {
                    Crud.delete((itemToDelete));
                    System.out.println("Item Apagado com sucesso");
                    goBack();
                }

                break;

            case "5":

                System.out.println("Organizar por:");
                System.out.println("0- Nome da tarefa");
                System.out.println("1- Descricao");
                System.out.println("2- Data de termino");
                System.out.println("3- Prioridade");
                System.out.println("4- Categoria");
                System.out.println("5- Status");
                System.out.println("6- Voltar");
                System.out.println("Digite o codigo:");

                String collumn = input.nextLine();
                if (collumn.equals("6")) {
                    menu();
                } else if (collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5")) {
                    Crud.sorter(collumn);
                    System.out.println("Lista reordenada com sucesso");
                    Crud.read();
                    goBack();
                } else {
                    System.out.println("Codigo invalido, tente novamente:");
                    collumn = input.nextLine();
                    while (!(collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5"))) {
                        System.out.println("Codigo invalido, tente novamente:");
                        collumn = input.nextLine();
                    }
                }

                break;

            case "6": {
                System.out.println("Pesquisar por:");
                System.out.println("0- Nome da tarefa");
                System.out.println("1- Descricao");
                System.out.println("2- Data de termino");
                System.out.println("3- Prioridade");
                System.out.println("4- Categoria");
                System.out.println("5- Status");
                System.out.println("6- Voltar");
                System.out.println("Digite o codigo:");

                collumn = input.nextLine();
                if (collumn.equals("6")) {
                    menu();
                } else if (collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5")) {

                    System.out.println("Palavra chave para a pesquisa:");
                    String word = input.nextLine();
                    Crud.search(collumn, word);

                    goBack();
                } else {
                    System.out.println("Codigo invalido, tente novamente:");
                    collumn = input.nextLine();
                    while (!(collumn.equals("0") || collumn.equals("1") || collumn.equals("2") || collumn.equals("3") || collumn.equals("4") || collumn.equals("5"))) {
                        System.out.println("Codigo invalido, tente novamente:");
                        collumn = input.nextLine();

                    }
                }
            }

            case "0": {
                input.close();
                break;
            }
            case "7": {
                Crud.count();
                goBack();
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
