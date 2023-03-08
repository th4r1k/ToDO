package todoapp.View;

import java.io.File;
import java.util.Scanner;

import todoapp.Controller.CrudTaskController;
import todoapp.Controller.TaskController;
import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.CrudTaskDAO;
import todoapp.Model.DAO.TaskDAO;

public class CreateTaskView {

    public static void menu() {
        File file = new File("data/tasks.csv");
        File tempfile = new File("data/temptasks.csv");

        TaskController taskController = new TaskController(new TaskDAO());
        CrudTaskController crudTaskController = new CrudTaskController(new CrudTaskDAO());

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

        String name = InputsView.inputName(input);

        if (taskController.taskExist(name, file)) {
            System.out.println("________________________________");
            System.out.println("Tarefa ja existe");
            Start.goBack();
        } else {
            String description = InputsView.inputDescription(input);
            String endDate = InputsView.inputDate(input);
            String endTime = InputsView.inputTime(input);
            String priority = InputsView.inputPriority(input);
            String category = InputsView.inputCategory(input);
            String status = InputsView.inputStatus(input);

            Task task = new Task(name, description, endDate, endTime, priority, category, status);
            return task;
        }
        return null;
    }

}