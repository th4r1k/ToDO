package todoapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import todoapp.Controller.CrudTaskController;
import todoapp.Model.Entity.Task;
import todoapp.Model.Service.CrudTaskService;
import todoapp.Model.Service.CrudTaskServiceInterface;

import java.io.File;

public class CrudTaskControllerTest {

    static CrudTaskController crudTaskController;
    static CrudTaskServiceInterface crudTaskService;
    static File file;

    @BeforeAll
    static void instanciaCrudTaskController(){
        crudTaskService = new CrudTaskService();
        crudTaskController = new CrudTaskController(crudTaskService);
        file = new File("data/testTasks.csv");
    }

    @AfterAll
    static void deleteTestFile(){
        file.delete();
    }

    @Test
    public void createTaskTest(){
        //given
        Task task = new Task("test", "description", "31/12/2023" , "22:22", "3", "category" , "todo");
        //when
        crudTaskController.createTask(task, file);
        //then


    }
}
