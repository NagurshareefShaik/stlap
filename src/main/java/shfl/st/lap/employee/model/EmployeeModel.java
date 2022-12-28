package shfl.st.lap.employee.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeeModel {
	
	private String employeeId;
	private LocalDateTime lastLoginTime;

}
