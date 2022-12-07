package shfl.st.lap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import shfl.st.lap.model.AuthRequest;
import shfl.st.lap.model.DisbursmentProcess;
import shfl.st.lap.model.Employee;
import shfl.st.lap.model.EmployeeModel;
import shfl.st.lap.model.JwtModel;
import shfl.st.lap.model.MobileUser;
import shfl.st.lap.service.AuthService;
import shfl.st.lap.service.EmployeeService;
import shfl.st.lap.service.OptService;
import shfl.st.lap.service.ReportService;

@RestController
public class StLapController {

	@Autowired
	OptService optService;

	@Autowired
	AuthService authService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ReportService reportService;

	@PostMapping("/generateOtp")
	public ResponseEntity<String> generateOtp(@RequestBody AuthRequest authRequest) {
		return optService.generateOtp(authRequest);
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody Employee employee) {
		return employeeService.registerUser(employee);
		
	}
	
	@PostMapping("/registerMobileUser")
	public String registerMobileUser(@RequestBody MobileUser mobileUser) {
		return employeeService.registerMobileUser(mobileUser);
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtModel> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		return authService.generateToken(authRequest);
	}
	
	@GetMapping("/generateReport")
	public ResponseEntity<byte[]> GenerateCustomerReport() throws Exception {
		System.out.println("genereate report method started COntroller");
		return reportService.generateCustomerReport();
		
	}

}
