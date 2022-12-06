package shfl.st.lap.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee{
	@Id
	private String employeeId;
	private String employeeName;
	private String email;
	private String mobileNumber;
	private String password;
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name  = "role_id")
	private String role;
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name  = "branch_id")
	private String branch;
	private int isActive;
	private Date lastLoginTime;
	private int currenltyLoggedIn;
	
}
