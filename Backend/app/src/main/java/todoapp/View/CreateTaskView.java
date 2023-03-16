package todoapp.View;

import java.util.Scanner;
import todoapp.Controller.TaskController;
import todoapp.Model.Entity.Task;
import todoapp.Model.DAO.TaskDAO;
import todoapp.Model.Service.TaskService;

public class CreateTaskView {

    public static void menu() {

        TaskController taskController = new TaskController(new TaskDAO(), TaskService.getInstance());

        Task task = newTaskForm(taskController);

        if (task != null) {
            taskController.addTask(task);
            taskController.sortByPriority();
            taskController.save();

            System.out.println("________________________________");
            System.out.println("Tarefa cadastrada com sucesso!");
            Start.goBack();
        }
    }

    public static Task newTaskForm(TaskController taskController) {
        Scanner input = new Scanner(System.in);

        String name = InputsView.inputName(input);

        if (taskController.verifyTaskExist(name)) {
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

            return new Task(name, description, endDate, endTime, priority, category, status);
        }
        return null;
    }

}