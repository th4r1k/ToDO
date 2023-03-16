package todoapp.Model.Service;

import todoapp.Model.Entity.Alarm;

import java.util.List;

public class AlarmObserver implements IObserver {
    private String email;

    public AlarmObserver(AlarmService alarmService, String email) {
        alarmService.attach(this);
        this.email = email;
    }

    @Override
    public void update(List<Alarm> alarmList ) {
        System.out.println("Enviar email para " + email + " informando o novo alarme criado" + alarmList );
    }


}
