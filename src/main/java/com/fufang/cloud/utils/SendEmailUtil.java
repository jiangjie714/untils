package com.fufang.cloud.utils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 */
public class SendEmailUtil {
	private static final String host; // smtp服务器
	private static final String user; // 用户名
	private static final String pwd; // 密码
	private static final String from; // 发件人地址

	static {
		InputStream inputStream = SendEmailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		host = p.getProperty("mailHost");
		user = p.getProperty("mailUserName");
		pwd = p.getProperty("mailUserPassword");
		from = p.getProperty("mailFrom");
	}

	/**
	 * 发送邮件功能 调用此功能才能发送邮件,多个toMail用;隔开
	 */
	public static void send(String toMail, String content, String subject) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			String[] tom = toMail.split(";");
			message.setFrom(new InternetAddress(from));
			Address[] address = new InternetAddress[tom.length];
			for (int i = 0; i < tom.length; i++) {
				address[i] = new InternetAddress(tom[i]);
			}
			message.addRecipients(Message.RecipientType.TO, address);
			message.setSubject(subject);
			message.setContent(content + "<br/>  此邮件为复方云系统邮件，请勿回复，谢谢！！！","text/html;charset=UTF-8");
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, pwd);
			// 邮件发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendEmailUtil.send("zhifuchen@qq.com","test","testsub");
	}
}
