package todoapp.Model.DAO;

import todoapp.Model.Entity.Alarm;

import java.util.List;

public interface AlarmDAOInterface {

    List<Alarm> getAllAlarms();
    void save(List<Alarm> alarmList);
}
