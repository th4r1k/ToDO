//package todoapp;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//import org.junit.Before;
//import org.junit.Test;
//import todoapp.Entity.Todo;
//import todoapp.Service.Crud;
//
//import static org.junit.Assert.*;
//import todoapp.Utils.Persist;
//public class CrudTest {
//
//    public List<String[]> columnValues = new ArrayList<>();
//
//        @Before
//                public void initialize(){
//            Persist.createFiles();
//        }
//
//    //   Read .csv file and update columnValues
//    public void init() {
//        try {
//            columnValues = new ArrayList<>();
//            Scanner scanner = new Scanner(new File("data/todos.csv"));
////            if(scanner.hasNextLine()){
////                scanner.nextLine();
////            }
//            while (scanner.hasNextLine()) {
//                String[] values = scanner.nextLine().split(",");
//                columnValues.add(values);
//            }
//            scanner.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void createTodoWasAddIntoCsvFile() {
//        //given
//        init();
//        int initial = columnValues.size();
//        int expected = initial + 1;
//        Todo todo = new Todo("test", "1", "20/01/2023", "22:50", "3", "none", "todo");
//
//        //when
//        Crud.create(todo);
//        init();
//
//        //then
//        assertEquals(expected, columnValues.size());
//    }
//
//    @Test
//    public void verifyCreateTodoAllDataWasAdd() {
//        //given
//        init();
//        Todo todo = new Todo("teste", "1", "20/01/2023", "22:50", "3", "none", "todo");
//        String[] expected = {"teste", "1", "20/01/2023", "22:50", "3", "none", "todo"};
//
//        //when
//        Crud.create(todo);
//        init();
//        String[] lastItem = columnValues.get(columnValues.size() - 1);
//
//        //then
//        assertEquals(Arrays.toString(expected), Arrays.toString(lastItem));
//
//    }
//
//
//    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoName() {
//        //given
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        int field = 0;
//        String newData = "newName";
//
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//
//
//        //then
//        assertEquals(newData, lastItemName);
//    }
//
//    //        field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoDescription() {
//        //given
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastItemDescription;
//        int field = 1;
//        String newData = "newDescription";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastItemDescription = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[1].trim();
//
//
//        //then
//        assertEquals(newData, lastItemDescription);
//
//    }
//
//    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoEndDate() {
//        //given
//
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastItemEndDate;
//        int field = 2;
//        String newData = "11/11/1111";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastItemEndDate = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[2].trim();
//
//
//        //then
//        assertEquals(newData, lastItemEndDate);
//    }
//
//
//    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoEndTime() {
//        //given
//
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastItemEndTime;
//        int field = 3;
//        String newData = "11:11";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastItemEndTime = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[3].trim();
//
//        //then
//        assertEquals(newData, lastItemEndTime);
//    }
//
//
//    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoPrority() {
//        //given
//
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastitemPriority;
//        int field = 4;
//        String newData = "3";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastitemPriority = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[4].trim();
//
//        //then
//        assertEquals(newData, lastitemPriority);
//    }
//
//
//    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoCategory() {
//        //given
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastitemCategory;
//        int field = 5;
//        String newData = "New Category";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastitemCategory = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[5].trim();
//
//        //then
//        assertEquals(newData, lastitemCategory);
//    }
//
//
//    //    //    field 0-name 1-description 2-endDate 3-endTime 4-priority 5-category 6-status
//    @Test
//    public void editTodoStatus() {
//        //given
//
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        String lastitemStatus;
//        int field = 6;
//        String newData = "New Status";
//
//        //when
//        Crud.update(lastItemName, field, newData);
//        init();
//        lastitemStatus = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[6].trim().replace("]", "");
//
//        //then
//   assertEquals(newData,lastitemStatus);
//    }
//
//
//   @Test
//    public void deleteTodoWasRemovedFromCsvFile() {
//        //given
//        init();
//        String lastItemName = Arrays.toString(columnValues.get(columnValues.size() - 1)).split(",")[0].replace("[", "");
//        int initial = columnValues.size();
//        int expected = initial - 1;
//
//
//        //when
//        Crud.delete(lastItemName);
//        init();
//
//        //then
//      assertEquals(expected,columnValues.size());
//    }
//
//}
