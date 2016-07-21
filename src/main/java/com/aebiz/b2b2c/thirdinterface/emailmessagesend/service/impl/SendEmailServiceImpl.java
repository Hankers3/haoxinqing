package com.aebiz.b2b2c.thirdinterface.emailmessagesend.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.thirdinterface.emailmessage.service.EmailMessageService;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModel;
import com.aebiz.b2b2c.thirdinterface.emailmessagesend.service.SendEmailService;

@Service
public class SendEmailServiceImpl implements SendEmailService {
	@Autowired
	private EmailMessageService emailMessageService;

	/**
	 * 邮件发送
	 * 
	 * @param email
	 *            发送的邮箱
	 * @param content
	 *            发送的内容
	 * @param title
	 *            发送的内容标题
	 */
	public void send(String email, String content, String title) {
		EmailMessageModel m = this.emailMessageService.getModel();
		if(m==null){
			return;
		}
		String serverAddress = m.getSmtpService();
		String userName = m.getSmtpUser();
		String pwd = m.getSmtpPassword();
		String receiver = email;
		String from = m.getSmtpAddress();
		String chineseName = m.getSmtpUserName();
		sendMail(serverAddress, userName, pwd, from, receiver, title, content,
				chineseName);
	}

	public static boolean sendMail(String serverAddress, String user,
			String pwd, String from, String emailReceiver, String subject,
			String htmlContents, String chineseName) {

		try {
			System.out.println("serverAddress = " + serverAddress);
			System.out.println("user = " + user);
			System.out.println("pwd = " + pwd);
			System.out.println("from = " + from);
			System.out.println("emailReceiver = " + emailReceiver);
			System.out.println("subject = " + subject);
			System.out.println("htmlContents = " + htmlContents);
			// 创建Properties对象
			Properties props = System.getProperties();
			// 创建信件服务器
			props.put("mail.smtp.localhost", "localHostAdress");
			props.put("mail.smtp.auth", "true");
			// 得到默认的对话对象
			Session session = Session.getDefaultInstance(props, null);

			// 创建一个消息，并初始化该消息的各项元素
			Message msg = new MimeMessage(session);
			// msg.setFrom(new InternetAddress(from));
			msg.setFrom(new InternetAddress("\""
					+ javax.mail.internet.MimeUtility.encodeText(chineseName)
					+ "\"<" + from + ">"));// 发件人
			InternetAddress[] address = { new InternetAddress(emailReceiver) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			// 后面的BodyPart将加入到此处创建的Multipart中
			Multipart mp = new MimeMultipart();
			// 设置内容
			BodyPart bp = new MimeBodyPart();
			bp.setContent(htmlContents + getMailCorpright(),
					"text/html;charset=gbk");
			mp.addBodyPart(bp);
			// Multipart加入到信件
			msg.setContent(mp);
			// 设置信件头的发送日期
			msg.setSentDate(new Date());
			msg.saveChanges();

			// 发送信件
			Transport trans = session.getTransport("smtp");
			trans.connect(serverAddress, user, pwd);

			if (trans.isConnected()) {
				trans.sendMessage(msg, msg.getAllRecipients());
				trans.close();
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static String getMailCorpright() {
		return "";
	}

	public static void main(String[] args) {
		sendMail("smtp.sina.cn", "aebiz@sina.cn", "aebiz123456", "aebiz@sina.cn",
				"914198379@qq.com", "测试邮件标题", "测试邮件内容", "全网数商");
	}
}
