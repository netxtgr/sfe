package io.sihuan.common.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    private static final String mailFrom = "mykj_yx@sihuanpharm.com";
    private static final String mailPWD = "Sihuan@123#";
    private static final String host = "mail.sihuanpharm.com";
    private static final String port = "465";
    private static Session session;
    static {
        //设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.socketFactory.port", port);
        props.setProperty("mail.smtp.auth", "true");
        //建立邮件会话
        session = Session.getDefaultInstance(props, new Authenticator() {
            //身份认证
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, mailPWD);
            }
        });
    }
    public static boolean sendMail(String toEmail, String title, String content){
        try {
            //建立邮件对象
            MimeMessage message = new MimeMessage(session);
            //设置邮件的发件人、收件人、主题
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(Message.RecipientType.TO, toEmail);
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            //发送邮件
            Transport.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        sendMail("liujiangtao@sihuanpharm.com", "测试标题", "测试内容");
    }

}
