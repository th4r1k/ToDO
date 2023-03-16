package todoapp.Model.Service;

import todoapp.Model.Entity.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskService {

    public List<Task> taskList = new ArrayList<>();
    private static TaskService instance = null;

    public TaskService() {}
    public TaskService(List<Task> taskList) {
        this.taskList = taskList;
    }
    public static synchronized TaskService getInstance(){
        if(instance==null){
            instance = new TaskService();
        }
        return instance;
    }

    public void addTask(Task task) {
        taskList.add(task);

    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public boolean verifyExists(String name) {
        boolean exist = false;
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
    
    public Task getTaskByName(String name) {
        Task foundTask = null;
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                foundTask = task;
            }
        }
        return foundTask;
    }
    
    public void search(String fieldToSearch, String name) {
        switch (fieldToSearch) {
            case "0":
                for (Task task : taskList) {
                    if (task.getName().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "1":
                for (Task task : taskList) {
                    if (task.getDescription().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "2":
                for (Task task : taskList) {
                    if (task.getEndDate().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "3":
                for (Task task : taskList) {
                    if (task.getEndTime().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "4":
                for (Task task : taskList) {
                    if (task.getPriority().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "5":
                for (Task task : taskList) {
                    if (task.getCategory().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            case "6":
                for (Task task : taskList) {
                    if (task.getStatus().equals(name)) {
                        System.out.println(task);
                    }
                }
                break;
            default:
                System.out.println("Tarefa nao encontrada");
        }
    }

    public void sorter(String fieldToSort) {
        switch (fieldToSort) {
            case "0":
                sortByName();
                break;
            case "1":
               sortByDescription();
                break;
            case "2":
               sortByEndDate();
                break;
            case "3":
               sortByEndTime();
                break;
            case "4":
                sortByPriority();
                break;
            case "5":
                sortByCategory();
                break;
            case "6":
                sortByStatus();
                break;
            default:
                System.out.println("Comando invalido");
        }
    }
    public void sortByName() {
        taskList.sort((task01, task02) -> task01.getName().toLowerCase().compareTo(task02.getName().toLowerCase()));
    }

    public void sortByDescription() {
        taskList.sort((task01, task02) -> task01.getDescription().toLowerCase().compareTo(task02.getDescription().toLowerCase()));
    }

    public void sortByEndTime() {
        taskList.sort((task01, task02) -> task01.getEndTime().toLowerCase().compareTo(task02.getEndTime().toLowerCase()));
    }

    public void sortByCategory() {
        taskList.sort((task01, task02) -> task01.getCategory().toLowerCase().compareTo(task02.getCategory().toLowerCase()));
    }

    public void sortByPriority() {
        taskList.sort((task01, task02) -> task01.getPriority().toLowerCase().compareTo(task02.getPriority().toLowerCase()));
    }

    public void sortByStatus() {
        taskList.sort((task01, task02) -> task01.getStatus().toLowerCase().compareTo(task02.getStatus().toLowerCase()));
    }

    public void sortByEndDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        taskList.sort((task01, task02) -> {
            try {
                return dateFormat.parse(task01.getEndDate().toLowerCase()).compareTo(dateFormat.parse((task02.getEndDate().toLowerCase())));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void count() {
        int todo = 0;
        int doing = 0;
        int done = 0;
        for (Task task : taskList) {
            if (task.getStatus().equals("todo")) {
                todo++;
            } else if (task.getStatus().equals("doing")) {
                doing++;
            } else done++;
        }
        System.out.println("itens a fazer(ToDo): " + todo);
        System.out.println("itens em andamento(Doing): " + doing);
        System.out.println("itens concluidos(Done): " + done);
    }

}