package shfl.st.lap.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import shfl.st.lap.jwt.JwtUtil;
import shfl.st.lap.model.AuthRequest;
import shfl.st.lap.model.LoanDetails;
import shfl.st.lap.service.ReportService;

@RestController
public class StLapController {
	
	@Autowired
	ReportService reportService;
	
	 @Autowired
	 private JwtUtil jwtUtil;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@GetMapping("/getApplicationData")
	public String getApplicationData() {
		return "Application Data";
		
	}
	@PostMapping("/nachSave")
	public String saveNachDetails() {
		
		return "nachSave";
	}
	@PostMapping("/downloadNach/{mandateId}")
	public String downloadNach() {
		
		return "nachDownload";
	}
	@GetMapping("/getDashboardData")
	public String getDashboardData() {
		return "Application Data";
		
	}
	
	@PostMapping("/generateReport")
	public String generateCustomerReport(@RequestBody LoanDetails loanDetails) throws JRException, IOException {
		return reportService.generateCustomerReport(loanDetails);
	}
	@PostMapping("/generateMonthReport")
	public String generateMonthDueReport(@RequestBody LoanDetails loanDetails) throws JRException, IOException {
		return reportService.generateMonthDueReport(loanDetails);
	}
	
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new UsernameNotFoundException("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
	

}
