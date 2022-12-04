package shfl.st.lap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.model.AuthRequest;
import shfl.st.lap.service.AuthService;
import shfl.st.lap.service.OptService;
import shfl.st.lap.service.UserService;

@RestController
public class StLapController {

	@Autowired
	OptService optService;

	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;

	@PostMapping("/generateOtp")
	public String generateOtp(@RequestBody AuthRequest authRequest) {
		return optService.generateOtp(authRequest);
	}
	
	@GetMapping("/check")
	public String check() {
		return "Validated";
	}
	
	@GetMapping("/insert")
	public String insertData() {
		return userService.insertUser();
		
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		return authService.generateToken(authRequest);
	}

}
