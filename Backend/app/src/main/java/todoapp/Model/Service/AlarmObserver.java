package todoapp.Model.Service;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import todoapp.Model.Entity.Alarm;

import java.io.IOException;
import java.util.List;

public class AlarmObserver implements IObserver {
    private String email;
    private static final String BOT_TOKEN = "Insert Bot token";
    private static final String CHAT_ID = "Insert Chat Id";

    public AlarmObserver(AlarmService alarmService, String email) {
        alarmService.attach(this);
        this.email = email;
    }

    @Override
    public void update(List<Alarm> alarmList ) {
        System.out.println("Enviar email para " + email + " informando o novo alarme criado" + alarmList );
        System.out.println("Enviado mensagem no seu Telegram");
        sendMessage(alarmList);
    }

    public void sendMessage(List<Alarm> alarmList ){
        try{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage");
        String json = "{\"chat_id\":\"" + CHAT_ID + "\",\"text\":\"" + "Novo alarme criado: " + alarmList + "\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpClient.execute(httpPost);
        httpClient.close();
        }catch (IOException e){
            System.out.println("falha ao enviar Mensagem via Telegram");
        }

    }


}
