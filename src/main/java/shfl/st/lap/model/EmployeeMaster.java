package shfl.st.lap.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeMaster {
	@Id
	private String employeeId;
	private String employeeName;
	private String email;
	private String mobileNumber;
	private String password;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name  = "role_id")
	private Role role;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name  = "branch_id")
	private BranchMaster branch;
	private int isActive;
	private Date lastLoginTime;
	private int currenltyLoggedIn;
	
}
