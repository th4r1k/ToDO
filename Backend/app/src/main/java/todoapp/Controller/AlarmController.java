package todoapp.Controller;

import todoapp.Model.DAO.AlarmDAOInterface;
import todoapp.Model.Entity.Alarm;
import todoapp.Model.Service.AlarmService;

public class AlarmController {

    AlarmDAOInterface alarmDAO;
    AlarmService alarmService;

    public AlarmController(AlarmDAOInterface alarmTask, AlarmService alarmService) {
        this.alarmDAO = alarmTask;
        this.alarmService = alarmService;
    }

    public void getAllAlarms(){
        alarmService.alarmList = alarmDAO.getAllAlarms();
    }

    public void save(){
        alarmDAO.save(alarmService.alarmList);
    }

    public void addAlarm(Alarm alarm){
        alarmService.addAlarm(alarm);
    }

    public void removeAlarm(Alarm alarm){
        alarmService.removeAlarm(alarm);
    }

    public void checkAlarms(){
        alarmService.checkAlarms();
    }

    public boolean verifyAlarmExists(String alarmName){
       return alarmService.verifyAlarmExist(alarmName);
    }

    public Alarm getAlarmByName(String alarmName){
        return alarmService.getAlarmByName(alarmName);
    }

    public void printAllAlarms(){
        for(Alarm alarm : alarmService.alarmList){
            System.out.println(alarm);
        }
    }

}
