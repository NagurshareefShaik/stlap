package shfl.st.lap.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import shfl.st.lap.jwt.JwtUtil;
import shfl.st.lap.model.AuthRequest;
import shfl.st.lap.model.MailData;
import shfl.st.lap.service.MailSevice;
import shfl.st.lap.service.UserService;

@RestController
public class StLapController {
	
	@Autowired
	MailSevice mailService;
	
	@Autowired
	UserService userService;
	
	 @Autowired
	 private JwtUtil jwtUtil;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody MailData mailData) throws JRException, IOException {
		return mailService.sendMail(mailData);
	}
	
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmployeeId(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new UsernameNotFoundException("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmployeeId());
    }
	

}
