package shfl.st.lap.employee.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_EMPLOYEE")
public class Employee {
	@Id
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile_number")
	private int mobileNumber;
	
	@Column(name = "password")
	private String password;
	
	//foreign key
	@Column(name = "role_id")
	private int roleId;
	
	//foreign key
	@Column(name = "branch_id")
	private int branchId;
	
	//foreign key
	@Column(name = "access_id")
	private int accessId;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;
	
	
}
