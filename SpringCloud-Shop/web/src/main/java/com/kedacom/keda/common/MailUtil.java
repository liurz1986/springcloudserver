package com.kedacom.keda.common;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailUtil {

	public static boolean sendMail(JavaMailSender mailSender, String mailContext, String from, String[] to,
			String subject) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(mailContext);

			mailSender.send(message);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
