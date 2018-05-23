package kr.mz.samples.ses.service;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class SesMailSender extends BaseMailSender{
	
	@Value("${aws.ses.port}")
	private String port;
	
	@Value("${aws.ses.host}")
	private String host;
	
	@Value("${aws.ses.username}")
	private String username;
	
	@Value("${aws.ses.password}")
	private String password;

	@Override
	protected void setPropertiesBySender(Properties props) {
		props.put("mail.smtp.port", port); 
	}

	@Override
	protected void send(MimeMessage msg, SMTPTransport transport) throws Exception {
		transport.connect(host, username, password);
		transport.sendMessage(msg, msg.getAllRecipients());
	}

	@Override
	protected Session getSession(Properties props) {
		return Session.getDefaultInstance(props);
	}
}
