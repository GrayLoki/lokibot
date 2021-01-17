package ru.home.mysuperbot;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.Properties;

public class sendEmail {
    private    String emailSubject = "Мой Telegram Bot!"; // Тема письма
    private   String emailFrom = "lokiloki781@gmail.com"; //
    private  String emailTo = "skuc090@gmail.com"; //

   /*public static void main(String[] args) {
       try {
           TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
           telegramBotsApi.registerBot(new MySuperBot());
       } catch (TelegramApiException e) {
           e.printStackTrace();
       }
      // Update update=new Update();
        sendEmail(MySuperBot.text);
    }*/
    public   void sendEmail(String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailTo);
        message.setFrom(emailFrom);
        message.setSubject(emailSubject);
        message.setText(text);

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername("lokiloki781@gmail.com");
        javaMailSender.setPassword("loki8879830753");
        javaMailSender.setPort(587);
        javaMailSender.setHost("smtp.gmail.com");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(props);
        javaMailSender.send(message);
    }
}
