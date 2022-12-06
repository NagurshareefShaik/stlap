package shfl.st.lap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import shfl.st.lap.jwt.JwtUtil;
import shfl.st.lap.model.AuthRequest;

@Service
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public ResponseEntity<String> generateToken(AuthRequest authRequest) throws Exception {
		Authentication authentication;
		try {
			authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmployeeId(), authRequest.getPassword()));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username/password");
		}
		return ResponseEntity.ok().body(jwtUtil.generateToken(authentication));

	}

}
