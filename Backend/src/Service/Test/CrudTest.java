package Backend.src.Service.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Backend.src.Entity.Todo;
import Backend.src.Service.Crud;


public class CrudTest {

   public List<String[]> columnValues = new ArrayList<>();


    //   Read .csv file and update columnValues
    public void init(){
        try{
            columnValues = new ArrayList<>();
            Scanner scanner = new Scanner(new File("data/todos.csv"));
//            if(scanner.hasNextLine()){
//                scanner.nextLine();
//            }
            while (scanner.hasNextLine()){
                String[] values = scanner.nextLine().split(",");
                columnValues.add(values);
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean createTodoWasAddIntoCsvFile(){
        //given
        init();
        int initial = columnValues.size();
        int expected = initial +1;
        Todo todo = new Todo("test", "1", "20/01/2023", "22:50", "3", "none", "todo");


        //when
        Crud.create(todo);
        init();

        //then
        if(!(columnValues.size() == expected)){
            System.out.println("Teste createTodoWasAddIntoCsvFile Falhou");

        }
        return  (columnValues.size() == expected);
    }

    public boolean verifyCreateTodoAllDataWasAdd(){
        //given
        init();
        Todo todo = new Todo("teste", "1", "20/01/2023", "22:50", "3", "none", "todo");
        String[] expected = {"teste","1","20/01/2023","22:50","3","none","todo"};

        //when
       Crud.create(todo);
       init();
       String[] lastItem = columnValues.get(columnValues.size() - 1);

        //then
        if(!(Arrays.toString(lastItem).equals(Arrays.toString(expected)))){
            System.out.println("Teste verifyCreateTodoAllDataWasAdd Falhou");
        }
        return (Arrays.toString(columnValues.get(columnValues.size() - 1)).equals(Arrays.toString(expected)) );

    }


//    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoName(){
        //given
        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        int field = 0;
        String newData = "newName";


        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastItemName= Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");



        //then
        if(!(lastItemName.equals(newData) )){
            System.out.println("Teste editTodoName Falhou");
        }
        return (lastItemName.equals(newData));
    }

//        field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoDescription() {
        //given
        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastItemDescription;
        int field = 1;
        String newData = "newDescription" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastItemDescription = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[1].trim();


        //then
        if (!(lastItemDescription.equals(newData) )) {
            System.out.println("Teste editTodoDescription Falhou");
        }
        return (lastItemDescription.equals(newData) );

    }

//    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoEndDate() {
        //given

        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastItemEndDate;
        int field = 2;
        String newData = "11/11/1111" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastItemEndDate = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[2].trim();


        //then
        if (!(lastItemEndDate.equals(newData))) {
            System.out.println("Teste editTodoEndDate Falhou");
        }
        return (lastItemEndDate.equals(newData));
    }


    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoEndTime() {
        //given

        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastItemEndTime ;
        int field = 3;
        String newData = "11:11" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastItemEndTime = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[3].trim();

        //then
        if (!(lastItemEndTime.equals(newData))) {
            System.out.println("Teste editTodoEndTime Falhou");
        }
        return (lastItemEndTime.equals(newData));
    }


//    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoPrority() {
        //given

        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastitemPriority ;
        int field = 4;
        String newData = "3" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastitemPriority = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[4].trim();

        //then
        if (!(lastitemPriority.equals(newData))) {
            System.out.println("Teste editTodoPrority Falhou");
        }
        return (lastitemPriority.equals(newData));
    }


    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoCategory() {
        //given

        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastitemCategory;
        int field = 5;
        String newData = "New Category" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastitemCategory = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[5].trim();

        //then
        if (!(lastitemCategory.equals(newData))) {
            System.out.println("Teste editTodoCategory Falhou");
        }
        return (lastitemCategory.equals(newData));
    }


    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
    public boolean editTodoStatus() {
        //given

        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String lastitemStatus ;
        int field = 6;
        String newData = "New Status" ;

        //when
        Crud.update(lastItemName, field, newData);
        init();
        lastitemStatus = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[6].trim().replace("]", "");

        //then
        if (!(lastitemStatus.equals(newData))) {
            System.out.println("Teste editTodoStatus Falhou");
        }
        return (lastitemStatus.equals(newData));
    }


    public boolean deleteTodoWasRemovedFromCsvFile(){
        //given
        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        int initial = columnValues.size();
        int expected = initial -1;


        //when
        Crud.delete(lastItemName);
        init();



        //then
        if(!(columnValues.size() == expected)){
            System.out.println("Teste deleteTodoWasRemovedFromCsvFile Falhou");

        }
        return  (columnValues.size() == expected);
    }

    public boolean verifyDeleteTodoDataWasRemoved(){
        //given
        init();
        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
        String initialLastTodoName = lastItemName;


        //when
        Crud.delete(lastItemName);
        init();

        lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[","");


        //then
        if((initialLastTodoName.equals(lastItemName))){
            System.out.println("Teste verifyDeleteTodoDataWasAdd Falhou");
        }
        return (!(initialLastTodoName.equals(lastItemName)));

    }

}
