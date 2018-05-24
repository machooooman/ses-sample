package kr.mz.samples.ses.service;
import com.sun.mail.smtp.SMTPTransport;

import kr.mz.samples.ses.model.Email;

/**
 * 메일 전송 인터페이스
 * @author user
 *
 */
public interface MailSender {
	SMTPTransport send(Email email) throws Exception;
}
