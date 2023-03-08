package todoapp.Controller;

import todoapp.Model.DAO.AlarmTaskDAOInterface;
import todoapp.Model.Entity.Alarm;

import java.io.File;

public class AlarmController {

    AlarmTaskDAOInterface alarmTask;

    public AlarmController(AlarmTaskDAOInterface alarmTask) {
        this.alarmTask = alarmTask;
    }

    public void createAlarm(Alarm alarm, File file, File alarmFile) {
        alarmTask.createAlarm(alarm, file, alarmFile);
    }

    public void readAlarms(File alarmFile) {
        alarmTask.readAlarms(alarmFile);
    }

    public void deleteAlarm(String name, File alarmFile, File tempAlarmFile) {
        alarmTask.deleteAlarm(name, alarmFile, tempAlarmFile);
    }

    public boolean verifyAlarm(String name, File alarmFile) {
        return alarmTask.verifyAlarm(name, alarmFile);
    }

    public void updateAlarm(String name, int todoField, String newData, File alarmFile, File tempAlarmFile) {
        alarmTask.updateAlarm(name, todoField, newData, alarmFile, tempAlarmFile);
    }

    public void startAlarm(File alarmFile, File tempAlarmFile) {
        alarmTask.alarm(alarmFile, tempAlarmFile);
    }
}
