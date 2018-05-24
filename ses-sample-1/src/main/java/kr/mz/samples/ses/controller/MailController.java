package kr.mz.samples.ses.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.smtp.SMTPTransport;

import kr.mz.samples.ses.model.Email;
import kr.mz.samples.ses.service.BaseMailSender;
import kr.mz.samples.ses.service.JavaMailSender;
import kr.mz.samples.ses.service.SesMailSender;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired 
	JavaMailSender javaSender;
	
	@Autowired
	SesMailSender sesSender;
	
	private Map<String, BaseMailSender> senderMap;
	
	@PostConstruct
	public void init() {
		senderMap = new HashMap<>();
		senderMap.put("/java", javaSender);
		senderMap.put("/ses", sesSender);
	}
	
	@PostMapping({"/java", "/ses"})
	public @ResponseBody ResponseEntity<?> send(@RequestBody Email email, HttpServletRequest request) throws Exception{
		ResponseEntity<?> response;
		String key = senderMap.keySet().stream()
				.filter(k -> request.getRequestURI().replace("/mail", "").startsWith(k))
				.collect(Collectors.joining());
		BaseMailSender sender = senderMap.get(key);
		SMTPTransport t = sender.send(email);
		if(t.getReportSuccess()) {
			response = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} else {
			response = new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
