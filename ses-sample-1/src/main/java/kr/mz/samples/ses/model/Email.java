package kr.mz.samples.ses.model;

public class Email {
	
	private String from;
	private String to;
	private String subject;
	private String body;

	public Email () {}
	
	public Email (String from, String to, String title, String body) {
		this.from = from;
		this.to = to;
		this.subject = title;
		this.body = body;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}