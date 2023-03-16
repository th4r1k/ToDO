package todoapp.Model.Service;

import todoapp.Model.Entity.Alarm;

import java.util.List;

public interface IObserver {
    void update(List<Alarm> alarmList );

}
