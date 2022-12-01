package shfl.st.lap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.MailData;

@Service
public class MailSevice {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	public String sendMail(MailData mailData) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
        msg.setTo(mailData.getTo());
        msg.setSubject("Testing from Spring Boot using mail service");
        msg.setText(mailData.getMsg());

        javaMailSender.send(msg);

		
		return "Mail send Successfully";
	}

}
