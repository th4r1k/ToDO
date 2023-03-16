package todoapp.Model.Service;

import todoapp.Model.Entity.Alarm;

import java.util.List;

public interface IObservable {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers(List<Alarm> alarmList );
}
