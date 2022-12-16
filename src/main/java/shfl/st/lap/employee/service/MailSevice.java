package shfl.st.lap.employee.service;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.jsonwebtoken.io.IOException;
import shfl.st.lap.employee.model.MailData;

@Service
public class MailSevice {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired     
	 Configuration fmConfiguration;
	
	@Autowired
	ResourceLoader loader;
	
	@Value("${spring.mail.username}")
	private String from;
	
	Logger log=LoggerFactory.getLogger(MailSevice.class);

	public String sendMail(MailData mailData){
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			Map<String,String> model=new HashMap<>();
			model.put("otp", mailData.getMsg());
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			Template t = fmConfiguration.getTemplate("otp-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(mailData.getTo());
			helper.setText(html, true);
			helper.setSubject("OTP For Login");
			helper.setFrom(from);
			javaMailSender.send(message);


		} catch (MessagingException | IOException | TemplateException | java.io.IOException e) {
			log.error(e.getMessage());
		}
		return "Mail sent successfully";
	}

}
