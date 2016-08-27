package com.vonzhou.learn.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 参考 `https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/`
 * Created by vonzhou on 2016/8/23.
 */
public class SendMailTLS {

    public static void main(String[] args) {

        final String username = "yourusername@gmail.com";
        final String password = "yourpassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "博观约取"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("944671035@qq.com"));
            message.setSubject("邮件主题");
            message.setText("你好,"
                    + "\n\n 这是一封神奇的邮件!");

            Transport.send(message);

            System.out.println("Done");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
