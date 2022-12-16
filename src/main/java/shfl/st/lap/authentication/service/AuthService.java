package shfl.st.lap.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import shfl.st.lap.authentication.jwt.JwtUtil;
import shfl.st.lap.authentication.model.AuthRequest;
import shfl.st.lap.authentication.model.JwtModel;
import shfl.st.lap.employee.model.EmployeeModel;
import shfl.st.lap.employee.service.EmployeeService;

@Service
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public ResponseEntity<JwtModel> generateToken(AuthRequest authRequest) throws Exception {
		Authentication authentication;
		JwtModel jwtModel=new JwtModel();
		try {
			authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmployeeId(), authRequest.getPassword()));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jwtModel);
		}
		ResponseEntity<EmployeeModel> employee=employeeService.getEmployeeData(authentication.getName());
		jwtModel.setJwToken(jwtUtil.generateToken(authentication));
		jwtModel.setUserId(employee.getBody().getEmployeeId());
		jwtModel.setLastLoginTime(employee.getBody().getLastLoginTime());
		return ResponseEntity.ok().body(jwtModel);

	}

}
