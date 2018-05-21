package kr.mz.samples.ses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.mz.samples.ses.model.Email;
import kr.mz.samples.ses.service.JavaMailSender;

@RestController
public class MailController {

	@Autowired 
	JavaMailSender mailSender;
	
	@PostMapping("/mail")
	public @ResponseBody ResponseEntity<?> send(@RequestBody Email email) {
		ResponseEntity<?> response;
		try {
			mailSender.send(email);
			response = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			response = new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
