package kr.mz.samples.ses.service;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import kr.mz.samples.ses.model.Email;

/**
 * 메일 전송 공통 서비스
 * @author user
 *
 */
public abstract class BaseMailSender implements MailSender{
	
	/**
	 * 유형별 프로퍼티 설정
	 * @param props
	 */
	abstract protected void setPropertiesBySender(Properties props);
	
	/**
	 * 유형별 메일 전송
	 * @param msg
	 * @param props
	 * @param session
	 * @throws Exception
	 */
	abstract protected void send(MimeMessage msg, SMTPTransport transport) throws Exception;
	
	/**
	 * 유형별 세션 얻기
	 * @param props
	 * @return
	 */
	abstract protected Session getSession(Properties props);
	
	/**
	 * 메일 전송
	 */
	@Override
	public void send(Email email){
		Properties props = getProperties();
		//Session session = Session.getDefaultInstance(props);
		Session session = getSession(props);
		try {
			MimeMessage msg = getMessage(session, email);
			SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
			send(msg, t);
			//log.info("success");
			//t.setReportSuccess(true);
		} catch(Exception e) {
			//log.error(e);
		}
	}
	
	/**
	 * 프로퍼티 설정
	 * @return
	 */
	public Properties getProperties() {
		Properties props = new Properties();
		//1. 공통 설정
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.EnableSSL.enable","true");
		//2. 유형별 설정
		setPropertiesBySender(props);
		return props;
	}
	
	/**
	 * 메시지 설정
	 * @param session
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public MimeMessage getMessage(Session session, Email email) throws Exception{
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email.getFrom()));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
		msg.setSubject(email.getSubject());
		msg.setContent(email.getBody(),"text/html; charset=UTF-8");
		return msg;
	}
}