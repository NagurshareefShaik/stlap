package shfl.st.lap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.Employee;
import shfl.st.lap.model.EmployeeModel;
import shfl.st.lap.model.MobileUser;
import shfl.st.lap.repo.EmployeeRepo;
import shfl.st.lap.repo.MobileRepo;
import shfl.st.lap.util.MobileNumberChecker;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MobileRepo mobileRepo;
	
	@Autowired
	MobileNumberChecker mobileNumberChecker;

	public String registerUser(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		String msg = "";
		Employee employeeData = employeeRepo.save(employee);
		if (Objects.nonNull(employeeData)) {
			msg = "Registration successfull";
		} else {
			msg = "Registration Not Successfull";
		}
		return msg;
	}

	public String registerMobileUser(MobileUser mobileUser) {
		String msg = "";
		MobileUser mobileUser1 = mobileRepo.save(mobileUser);
		if (Objects.nonNull(mobileUser1)) {
			msg = "Registration successfull";
		} else {
			msg = "Registration Not Successfull";
		}
		return msg;
	}

	public ResponseEntity<EmployeeModel> getEmployeeData(String userId) {
			EmployeeModel employeeModel = new EmployeeModel();
			if(mobileNumberChecker.check(userId)) {
				MobileUser mobileUser=mobileRepo.findByMobileNumber(userId);
				employeeModel.setEmployeeId(mobileUser.getUserName());
				employeeModel.setLastLoginTime(mobileUser.getLastLoginTime());
				return ResponseEntity.status(HttpStatus.OK).body(employeeModel);
			}
			Employee employee = employeeRepo.findByEmployeeId(userId);
			employeeModel.setEmployeeId(employee.getEmployeeName());
			employeeModel.setLastLoginTime(employee.getLastLoginTime());
			return ResponseEntity.status(HttpStatus.OK).body(employeeModel);
	}

}
