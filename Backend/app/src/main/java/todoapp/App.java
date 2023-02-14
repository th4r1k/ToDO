package todoapp;

import todoapp.Service.Crud;
import todoapp.Utils.Menu.Start;
import todoapp.Utils.Persist;

public class App {
    public static void main(String[] args) {

        Persist.createFiles();

        System.out.println("*** Bem vindo ao ToDoApp ***");
        Crud.alarm();
        Start.menu();

    }
}
