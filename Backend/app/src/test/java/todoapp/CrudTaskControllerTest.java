package todoapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import todoapp.Controller.CrudTaskController;
import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.CrudTaskDAO;
import todoapp.Model.DAO.CrudTaskDAOInterface;
import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;

public class CrudTaskControllerTest {

    static CrudTaskController crudTaskController;
    static CrudTaskDAOInterface crudTaskDAO;
    static File file;
    static File tempFile;

    @BeforeAll
    static void instanciaCrudTaskController() throws IOException {
        crudTaskDAO = new CrudTaskDAO();
        crudTaskController = new CrudTaskController(crudTaskDAO);
        file = new File("data/testTasks.csv");
        file.createNewFile();
        tempFile = new File("data/tempTestTasks.csv");
        tempFile.createNewFile();
    }

    @AfterAll
    static void deleteTestFile(){
        file.delete();
        tempFile.delete();
    }

    @Test
    public void createTaskTest(){
        //given
        Task task = new Task("test", "description", "31/12/2023" , "22:22", "3", "category" , "todo");
        boolean expected = true;
        //when
        boolean result = crudTaskController.createTask(task, file);
        //then
        assertEquals(expected, result);

    }
    @Test
    public void readTaskTest(){
        //given
        boolean expected = true;
        //when
        boolean result = crudTaskController.readAllTasks(file);
        //then
        assertEquals(expected, result);

    }
    @Test
    public void updateTaskTest(){
        //given
        boolean expected = true;
        //when
        boolean result = crudTaskController.updateTask("test", 1, "newDescription", file, tempFile);
        //then
        assertEquals(expected, result);

    }

    @Test
    public void deleteTaskTest(){
        //given
        boolean expected = true;
        //when
        boolean result = crudTaskController.deleteTask("test", file, tempFile);
        //then
        assertEquals(expected, result);

    }

}
