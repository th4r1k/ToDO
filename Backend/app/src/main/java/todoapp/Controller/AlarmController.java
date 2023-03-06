package todoapp.Controller;

import todoapp.Service.AlarmTaskServiceInterface;

import java.io.File;

public class AlarmController {

    AlarmTaskServiceInterface alarmTask;

    public AlarmController(AlarmTaskServiceInterface alarmTask) {
        this.alarmTask = alarmTask;
    }

    public void createAlarm(String name, String dateAlarm, String timeAlarm, File file, File alarmFile) {
        alarmTask.createAlarm(name, dateAlarm, timeAlarm, file, alarmFile);
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
