import Service.Crud;
import utils.Persist;
import utils.Start;

public class Main {
    public static void main(String[] args) {

        Persist.createFiles();
        System.out.println("*** Bem vindo ao ToDoApp ***");
        Crud.alarm();
        Start.menu();

    }
}