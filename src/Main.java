package src;

import src.Service.Crud;
import src.Utils.Persist;
import src.Utils.Menu.Start;

public class Main {
    public static void main(String[] args) {

         Persist.createFiles();
         System.out.println("*** Bem vindo ao ToDoApp ***");
         Crud.alarm();
         Start.menu();


    }
}