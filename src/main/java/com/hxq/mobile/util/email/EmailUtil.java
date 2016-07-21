package com.hxq.mobile.util.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;




public class EmailUtil {
/*	static MailTemplateService service = new MailTemplateService();
	static EmailHistoryService emailHistoryService = new EmailHistoryService();*/

	//连接测试机的方法
	
	private static final String MAIL_HOST = "smtp.exmail.qq.com";
	private static final Integer SMTP_HOST_PORT = 25;
	private static final String SMTP_AUTH_USER = "liuyang@hxqydyl.com";
	private static final String SMTP_AUTH_PWD  = "Yjbn1990";
	
	
	private static Integer IS_FORMAL_SERVER = 1; //是否是正式服务器: 0:不是,1是
	
	//得到是否是正式服务器
	public static synchronized int getIsFormalServer(){
		if(IS_FORMAL_SERVER == null){
			IS_FORMAL_SERVER = Integer.valueOf("1");
		}
		
		return IS_FORMAL_SERVER;
		
	}
	
		
	
	public static boolean send(String subject, String text, String from, String to, boolean ishtml) {
		Session session = getSession();
		// session.setDebug(true);
		Message message = new MimeMessage(session);
		try {
			message.setSubject(subject);
			if (ishtml) {
				message.setContent(text, "text/html;charset=utf-8");
			} else {
				message.setText(text);
			}
			

			Transport transport = session.getTransport();
			
			transport.connect(MAIL_HOST, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
			
			//发件人为空,设置为默认值
			if(StringUtil.isEmpty(from)){
				from = SMTP_AUTH_USER;
			}
			
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			message.setSentDate(new Date());

		
			
			if (!transport.isConnected()) {
				return false;
			} else {
				transport.sendMessage(message, new InternetAddress[] { new InternetAddress(to) });
				transport.close();
			}
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean sendTemplate(Mail mail) {
		
		return sendTemplate2(mail.getSubject(), MailTemplate.getContext("template"), mail.getMap(), mail.getFrom(), mail.getTo());
	}

	public static boolean sendTemplate2(String subject, String text, Map kv, String from, String to) {
		StringBuilder sb = replaceHtml(text, kv);
		return send(subject, sb.toString(), from, to, true);
	}

	private static StringBuilder replaceHtml(String text, Map kv) {
		Pattern kvp = Pattern.compile("(%[a-zA-Z0-9]+%)");
		Matcher m = kvp.matcher(text);
		StringBuilder sb = new StringBuilder(text.length() + 256);

		int pos = 0;
		while (m.find()) {
			sb.append(text.substring(pos, m.start(1)));
			String key = m.group(1);
			key = key.substring(1, key.length() - 1);//去掉%%
			Object c = kv.get(key);
			if(c != null)
				sb.append(c);
			pos = m.end(1);
		}
		sb.append(text.substring(pos, text.length()));
		return sb;
	}


	
	public static Session getSession() {
		Properties props = setProperties();
	//	props.setProperty("mail.transport.protocol", "smtp");// 什么方式连接
		Session session = Session.getInstance(props);
		return session;
	}
	

	private static Properties setProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		return props;
	}
	

	
	
	public static void main(String[] args) throws Exception {
		long nowtime = System.currentTimeMillis();
		Session session = getSession();
		Transport transport = session.getTransport();
		
		//transport.connect("mail.ebinf.com", 465, "oa@ebinf.com", "sjwx_1qaz2wsx");
		transport.connect();
		for (int i = 0; i < 1; i++) {
			Message message = new MimeMessage(session);
			message.setSubject("Testing SMTP-SSL" + i);
			message.setText("This is a test" + i);
			message.setFrom(new InternetAddress("liuyang6@ebinf.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("liuyang6@ebinf.com", false));
			message.setSentDate(new Date());
			transport.sendMessage(message, new InternetAddress[] { new InternetAddress("liuyang6@ebinf.com") });
		}
		transport.close();
		System.out.println(System.currentTimeMillis() - nowtime);
	}
	
}
