package kr.mz.samples.ses.service;
import kr.mz.samples.ses.model.Email;

/**
 * 메일 전송 인터페이스
 * @author user
 *
 */
public interface MailSender {
	void send(Email email);
}
