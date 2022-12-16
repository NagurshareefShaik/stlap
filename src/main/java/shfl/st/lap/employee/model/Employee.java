package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@Column(name = "employee_id")
	private String employeeId;
	@Column(name = "employee_name")
	private String employeeName;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name = "password")
	private String password;
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "branch_id")
	private int branch;
	@Column(name = "is_active")
	private int isActive;
	@Column(name = "last_login_time")
	private String lastLoginTime;
	@Column(name = "currently_logged_in")
	private int currenltyLoggedIn;

}
