package kr.mz.samples.ses.service;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

/**
 * Gmail SMTP
 * @author user
 *
 */
@Service
public class JavaMailSender extends BaseMailSender{
	
	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.smtp.port}")
	private String port;
	
	@Value("${mail.smtp.username}")
	private String username;
	
	@Value("${mail.smtp.password}")
	private String password;
	
	@Override
	protected void setPropertiesBySender(Properties props) {
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void send(MimeMessage msg, SMTPTransport transport) throws Exception {
		transport.send(msg);
	}

	@Override
	protected Session getSession(Properties props) {
		return Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
	}
	
}
