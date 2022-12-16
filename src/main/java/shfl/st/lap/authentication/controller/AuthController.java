package shfl.st.lap.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.authentication.model.AuthRequest;
import shfl.st.lap.authentication.model.JwtModel;
import shfl.st.lap.authentication.service.AuthService;


@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtModel> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		return authService.generateToken(authRequest);
	}

}
