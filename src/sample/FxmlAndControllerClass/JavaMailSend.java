package sample.FxmlAndControllerClass;



import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailSend {
    public static void sendMail(String recepient,String nume,String parola) throws Exception
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        String myAccountEmail ="chatapplicationfis@gmail.com";
        String myAccountPassword="StanDarius24";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,myAccountPassword);
            }
        });
                Message message = prepareMessage(session,myAccountEmail,recepient,nume,parola);
        Transport.send(message);

    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String nume,String parola){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Welcome to ChatApplication JavaFis Project");
            message.setText("Hey There\n" + "Your Account:\n Username: " +nume +"\n Password: " +parola +"\n" + "Thanks for all!");
            return message;
        } catch (Exception exception)
        {
            Logger.getLogger(JavaMailSend.class.getName()).log(Level.SEVERE,null,exception);
        }
        return null;
    }
}
