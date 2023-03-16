package todoapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import todoapp.Controller.AlarmController;
import todoapp.Model.DAO.AlarmDAO;
import todoapp.Model.DAO.AlarmDAOInterface;
import todoapp.Model.Entity.Alarm;
import todoapp.Model.Service.AlarmService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmControllerTest {
    static AlarmController alarmController;
    static AlarmDAOInterface mockDAO;
    static AlarmService alarmService;
    static Alarm alarm1;
    static Alarm alarm2;

    @BeforeAll
    static void instanciaCrudTaskController() {
        mockDAO = mock(AlarmDAO.class);
        alarmService = AlarmService.getInstance();

        alarmController = new AlarmController(mockDAO, alarmService);

        alarm1 = new Alarm("test1", "31/12/2023", "22:22");
        alarm2 = new Alarm("test2", "description", "31/12/2023");
        alarmService.addAlarm(alarm1);
    }

    @Test
    public void getAllAlarmsTest() {
        //given

        List<Alarm> expected = new ArrayList<>();
        expected.add(alarm1);
        expected.add(alarm2);

        when(mockDAO.getAllAlarms()).thenReturn(expected);

        //when
        alarmController.getAllAlarms();
        List<Alarm> result = alarmService.alarmList;

        //then
        assertEquals(expected, result);

    }
    @Test
    public void createAlarmTest() {
        //given
        int size = alarmService.alarmList.size();
        int expected = size + 1;

        //when
        alarmController.addAlarm(alarm1);
        int result = alarmService.alarmList.size();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void removeAlarmTest() {
        //given
        int size = alarmService.alarmList.size();
        int expected = size - 1;

//        //when
        alarmController.removeAlarm(alarm1);
        int result = alarmService.alarmList.size();

//        //then
        assertEquals(expected, result);
    }

    @Test
    public void verifyAlarmExistsTest() {
        //given
        alarmController.addAlarm(alarm1);
        boolean expected = true;

        //when
        boolean result = alarmController.verifyAlarmExists("test1");

        //then
        assertEquals(expected, result);
    }

    @Test
    public void getAlarmByNameTest() {
        //given
        alarmController.addAlarm(alarm1);
        Alarm expected = alarm1;

        //when
        Alarm result = alarmController.getAlarmByName("test1");

        //then
        assertEquals(expected, result);
    }

}
