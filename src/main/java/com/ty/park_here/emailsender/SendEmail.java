package com.ty.park_here.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
	
	@Autowired
	private JavaMailSender javaMailSender ;
	
	public void sendMail(String toMail,String body,String subject) {
		
		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
		simpleMailMessage.setFrom("parkheretyss8@gmail.com");
		simpleMailMessage.setTo(toMail);
		simpleMailMessage.setText(body);
		simpleMailMessage.setSubject(subject);
		
		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail Sent.....");
	}

}
