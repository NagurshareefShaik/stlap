package shfl.st.lap.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.authentication.model.AuthRequest;
import shfl.st.lap.employee.model.Employee;
import shfl.st.lap.employee.model.MobileUser;
import shfl.st.lap.employee.service.EmployeeService;
import shfl.st.lap.employee.service.OptService;


@RestController
public class EmployeeController {

	@Autowired
	OptService optService;

	
	@Autowired
	EmployeeService employeeService;
	
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


}
