package com.his.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public boolean sendEmail(String to, String from, String subject, String text) throws Exception {
		boolean flag = true;
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			//helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);// IT is html text
			emailSender.send(message);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;

	}
}
