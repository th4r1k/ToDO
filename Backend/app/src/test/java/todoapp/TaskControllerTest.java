package todoapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import todoapp.Controller.TaskController;
import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.DAO.TaskDAOInterface;
import todoapp.Model.Service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskControllerTest {

    static TaskController taskController;
    static TaskDAOInterface mockDAO;
    static TaskService taskService;
    static Task task1;
    static Task task2;

    @BeforeAll
    static void instanciaTaskController() {
        mockDAO = mock(TaskDAO.class);
        taskService = TaskService.getInstance();

        taskController = new TaskController(mockDAO, taskService);

        task1 = new Task("test1", "description", "31/12/2023", "22:22", "3", "category", "todo");
        task2 = new Task("test2", "description", "31/12/2023", "22:22", "3", "category", "todo");
        taskService.addTask(task1);
    }


    @Test
    public void getAllTasksTest() {
        //given

        List<Task> expected = new ArrayList<>();
        expected.add(task1);
        expected.add(task2);

        when(mockDAO.getAllTasks()).thenReturn(expected);

        //when
        taskController.getAllTasks();
        List<Task> result = taskService.taskList;

        //then
        assertEquals(expected, result);

    }

    @Test
    public void createTaskTest() {
        //given
        int size = taskService.taskList.size();
        int expected = size + 1;

        //when
        taskController.addTask(task1);
        int result = taskService.taskList.size();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void removeTaskTest() {
        //given
        int size = taskService.taskList.size();
        int expected = size - 1;
//
//        //when
        taskController.removeTask(task1);
        int result = taskService.taskList.size();
//
//        //then
        assertEquals(expected, result);
    }

    @Test
    public void verifyTaskExistsTest() {
        //given
        taskController.addTask(task1);
        boolean expected = true;

        //when
        boolean result = taskController.verifyTaskExist("test1");

        //then
        assertEquals(expected, result);
    }

    @Test
    public void getTaskByNameTest() {
        //given
        taskController.addTask(task1);
        Task expected = task1;

        //when
        Task result = taskController.getTaskByName("test1");

        //then
        assertEquals(expected, result);
    }

}
