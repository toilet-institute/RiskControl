package com.jxlt.udic.riskcontrol.website.util;


import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * SendMailUtil
 *
 * @author zhout26
 * @version 1.0
 * 2020/12/7 14:11
 **/
public class SendMailUtil {

    public static void sendMail(String subject, String content, String toEmailAddres)
            throws Exception
    {
        String host = "10.236.3.246";
        String port = "587";
        String auth = "true";
        String protocol = "smtp";
        String mailFrom = "zhout26@chinaunicom.cn";
        String personalName = "zhout26";
        String username = "zhout26@chinaunicom.cn";
        String password = "!Enter1687";
        String mailDebug = "false";
        String contentType = null;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth == null ? "true" : auth);
        props.put("mail.transport.protocol", protocol == null ? "smtp" : protocol);
        props.put("mail.smtp.port", port == null ? "25" : port);
        props.put("mail.debug", mailDebug == null ? "false" : mailDebug);
        Session mailSession = Session.getInstance(props);

        MimeMessage message = new MimeMessage(mailSession);

        message.setSubject(subject);

        message.setContent(content, contentType == null ? "text/html;charset=UTF-8" : contentType);

        message.setSentDate(new Date());
        InternetAddress address = new InternetAddress(mailFrom, personalName);

        message.setFrom(address);

        message.setRecipients(Message.RecipientType.TO, toEmailAddres);
        Transport transport = null;
        transport = mailSession.getTransport();
        message.saveChanges();

        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
