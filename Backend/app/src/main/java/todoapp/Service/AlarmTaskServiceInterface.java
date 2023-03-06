package todoapp.Service;

import java.io.File;

public interface AlarmTaskServiceInterface {

    void createAlarm(String name, String dateAlarm, String timeAlarm, File file, File alarmFile);
    void readAlarms(File alarmFile);
    void deleteAlarm(String name, File alarmFile, File tempAlarmFile);
    boolean verifyAlarm(String name, File alarmFile);
    void updateAlarm(String name, int todoField, String newData, File alarmFile, File tempAlarmFile);
    void alarm(File alarmFile, File tempAlarmFil);
}
