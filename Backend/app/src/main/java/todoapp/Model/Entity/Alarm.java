package todoapp.Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Alarm {

    private String name;
    private String dateAlarm;
    private String timeAlarm;

    public Alarm(){};
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDateAlarm(String dateAlarm) {
        this.dateAlarm = dateAlarm;
    }

    public void setTimeAlarm(String timeAlarm) {
        this.timeAlarm = timeAlarm;
    }

    @Override
    public String toString() {
        return this.name + "," + this.dateAlarm + "," + this.timeAlarm;
    }

}
