package lk.pahana.edu.pahana_edu_billing_system.util.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend {

    public static void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        final String from = "pahanedu.books@gmail.com";
        final String password = "xtsa diyr qrla acng";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(htmlContent, "text/html; charset=utf-8");

        Transport.send(msg);
    }
}
