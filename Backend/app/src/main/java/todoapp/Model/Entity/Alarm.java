package todoapp.Model.Entity;

public class Alarm {

    String name;
    String dateAlarm;
    String timeAlarm;

    public Alarm(String name, String dateAlarm, String timeAlarm) {
        this.name = name;
        this.dateAlarm = dateAlarm;
        this.timeAlarm = timeAlarm;
    }

    public String getName() {
        return name;
    }

    public String getDateAlarm() {
        return dateAlarm;
    }

    public String getTimeAlarm() {
        return timeAlarm;
    }

}
