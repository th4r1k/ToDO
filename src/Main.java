package src;

import src.Service.Crud;
import src.Service.Test.CrudTest;
import src.Utils.Persist;
import src.Utils.Menu.Start;

public class Main {
    public static void main(String[] args) {

        Persist.createFiles();
        CrudTest test = new CrudTest();
        boolean t1 = test.createTodoWasAddIntoCsvFile();
        boolean t2 = test.verifyCreateTodoAllDataWasAdd();
        boolean t3 = test.editTodoName();
        boolean t4 = test.editTodoDescription();
        boolean t5 = test.editTodoCategory();
        boolean t6 = test.editTodoStatus();
        boolean t7 = test.editTodoPrority();
        boolean t8 = test.editTodoEndDate();
        boolean t9 = test.editTodoEndTime();
        boolean t10 = test.deleteTodoWasRemovedFromCsvFile();
        boolean t11 = test.verifyDeleteTodoDataWasRemoved();


        if(t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 && t10 && t11) {
            System.out.println("Todos os testes realizados nao aparesentaram problemas");
            System.out.println("*** Bem vindo ao ToDoApp ***");
            Crud.alarm();
            Start.menu();
        }

    }
}