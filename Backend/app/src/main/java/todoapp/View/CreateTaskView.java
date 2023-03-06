package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Entity.Task;
import todoapp.Service.CrudTaskService;
import todoapp.Service.TaskService;
import todoapp.Utils.Validate;

public class CreateTaskView {

    public static void menu() {
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");

        TaskController taskController = new TaskController(new TaskService());
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskService());

        Task task = newTaskForm(taskController, file);

        if (task != null) {
            crudTaskController.createTask(task, file);
            taskController.sortTask("4", file, tempfile);
            System.out.println("________________________________");
            System.out.println("Tarefa cadastrada com sucesso!");
            Start.menu();
        }
    }

    public static Task newTaskForm(TaskController taskController, File file) {
        Scanner input = new Scanner(System.in);

        String name = Validate.inputName(input);

        if (taskController.taskExist(name, file)) {
            System.out.println("________________________________");
            System.out.println("Tarefa ja existe");
            Start.goBack();
        } else {
            String description = Validate.inputDescription(input);
            String endDate = Validate.inputDate(input);
            String endTime = Validate.inputTime(input);
            String priority = Validate.inputPriority(input);
            String category = Validate.inputCategory(input);
            String status = Validate.inputStatus(input);

            Task task = new Task(name, description, endDate, endTime, priority, category, status);
            return task;
        }
        return null;
    }

}