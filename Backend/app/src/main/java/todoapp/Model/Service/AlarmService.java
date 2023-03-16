package todoapp.Model.Service;

import todoapp.Model.Entity.Alarm;
import todoapp.Model.Entity.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmService implements IObservable {
    public List<Alarm> alarmList = new ArrayList<>();
    private List<IObserver> observers = new ArrayList<>();
    private static AlarmService instance = null;
    public AlarmService() {}
    public static synchronized AlarmService getInstance(){
        if(instance==null){
            instance = new AlarmService();
        }
        return instance;
    }

    public void addAlarm(Alarm alarm) {
        alarmList.add(alarm);
        notifyObservers(alarmList);
    }

    public void removeAlarm(Alarm alarm) {
        alarmList.remove(alarm);
    }

    public boolean verifyAlarmExist(String name) {
        boolean exist = false;
        for (Alarm alarm : alarmList) {
            if (alarm.getName().equals(name)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public void checkAlarms() {
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String timeNow = new SimpleDateFormat("HH:mm").format(date);

        for (Alarm alarm : alarmList) {
            try{
                if (alarm.getDateAlarm().equals(today) & new SimpleDateFormat("HH:mm").parse(timeNow).after(new SimpleDateFormat("HH:mm").parse(alarm.getTimeAlarm()))) {
                    removeAlarm(alarm);
                    System.out.println("********************ALARME*******************");
                    System.out.println("O alarme da tarefa: '" + alarm.getName() + "' foi removido pois ja passou a data de termino");
                    System.out.println("********************ALARME*******************");
                    break;
                } else if (new SimpleDateFormat("dd/MM/yyyy").parse(today).after(new SimpleDateFormat("dd/MM/yyyy").parse(alarm.getDateAlarm()))) {
                    removeAlarm(alarm);
                    System.out.println("********************ALARME*******************");
                    System.out.println("O alarme da tarefa: '" + alarm.getName() + "' foi removido pois ja passou a data de termino");
                    System.out.println("********************ALARME*******************");
                    break;
                } else if (alarm.getDateAlarm().equals(today) & new SimpleDateFormat("HH:mm").parse(timeNow).after(new SimpleDateFormat("HH:mm").parse(alarm.getTimeAlarm()))) {
                    System.out.println("********************ALARME*******************");
                    System.out.println("A tarefa:" + " " + alarm.getName() + ", " + "Data de termino" + " " + alarm.getDateAlarm() + " " + "as: " + alarm.getTimeAlarm());
                    System.out.println("********************ALARME*******************");
                }
            }catch (ParseException e){
                System.out.println("Data no formato errado");
            }
        }
    }
    public Alarm getAlarmByName(String name) {
        Alarm foundTask = null;
        for (Alarm alarm : alarmList) {
            if (alarm.getName().equals(name)) {
                foundTask = alarm;
            }
        }
        return foundTask;
    }


    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(List<Alarm> alarmList) {
        for (IObserver observer : observers) {
            observer.update(alarmList);
        }


    }
}