package com.vonzhou.learn.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * 实现发送邮件
 * Created by vonzhou on 2016/8/23.
 */
public class MailSender {
    private static final String PROTOCOL = "smtp";
    private static final String HOST = "smtp.163.com";
    private static final String SENDER = "xxxx@163.com";   // 发送者的邮箱
    private static final String AUTH_USER = "xxx@163.com"; // 认证邮箱
    private static final String PASSWD = "xxxxx";  // 认证密码

    public static boolean sendEmail(String toAddress) {
        String toEmail = toAddress;
        try {
            Properties props = new Properties();
            // set smtp server
            props.setProperty("mail.transport.protocol", PROTOCOL);
            props.setProperty("mail.smtp.host", HOST);
            props.setProperty("mail.smtp.auth", "true");

            // get session
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(AUTH_USER, PASSWD);
                }
            };
//            Session session = Session.getDefaultInstance(props, auth);
            Session session = Session.getInstance(props, auth);
//            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER, "发送者"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("邮件主题");

            message.setText("这封邮件内容很好。");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
            System.out.println("邮件发送成功!");
        } catch (Exception e) {
            System.out.println("邮件发送失败！" + e.getMessage());
            return false;
        }
        return true;
    }

}
