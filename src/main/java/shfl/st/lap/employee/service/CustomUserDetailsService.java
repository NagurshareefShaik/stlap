package shfl.st.lap.employee.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shfl.st.lap.employee.model.Employee;
import shfl.st.lap.employee.model.MobileUser;
import shfl.st.lap.employee.repo.EmployeeRepo;
import shfl.st.lap.employee.repo.MobileRepo;
import shfl.st.lap.employee.util.MobileNumberChecker;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private MobileRepo mobileRepo;
	
	@Autowired
	private MobileNumberChecker checker;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(checker.check(username)) {
			MobileUser mobileUser = mobileRepo.findByMobileNumber(username);
			return new org.springframework.security.core.userdetails.User(mobileUser.getMobileNumber(), mobileUser.getOtp(),
					Collections.singleton(new SimpleGrantedAuthority("Sales")));
		}
		
		Employee employeeMaster = employeeRepo.findByEmployeeId(username);
		return new org.springframework.security.core.userdetails.User(employeeMaster.getEmployeeId(), employeeMaster.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority("Sales")));
	}
}
