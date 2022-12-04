package shfl.st.lap.service;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import shfl.st.lap.model.AuthRequest;
import shfl.st.lap.model.MailData;
import shfl.st.lap.model.MobileUser;
import shfl.st.lap.repo.MobileRepo;
import shfl.st.lap.util.MobileNumberChecker;

@Service
public class OptService {
	
	@Autowired
	MailSevice mailSevice;
	
	@Autowired
	MobileRepo mobileRepo;
	
	@Autowired
	private MobileNumberChecker checker;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	public String generateOtp(AuthRequest authRequest) {
		if(checker.check(authRequest.getEmployeeId())) {
			MobileUser mobileUser=mobileRepo.findByMobileNumber(authRequest.getEmployeeId());
			if(Objects.nonNull(mobileUser)) {
				Random random = new Random();
				int otp = 100000 + random.nextInt(900000);
				MailData data=new MailData();
				data.setTo("shareefchinnu561@gmail.com");
				data.setMsg(String.valueOf(otp));
				mobileUser.setOtp(passwordEncoder.encode(String.valueOf(otp)));
				mobileRepo.save(mobileUser);
				mailSevice.sendMail(data);
				Message.creator(new PhoneNumber("+91"+authRequest.getEmployeeId()), new PhoneNumber("+16294006166"),
				         otp+" is your OTP to access Sundaram Home. OTP is confidential and valid for 2 minutes.For security reasons DO NOT share this OTP with anyone").create();
				return "OTP Generated suceessfully";
			}else {
				return "Mobile Number Not Registered";
			}
		}
		else {
			return "Enter Valid Mobile Number";
		}
	}

}
