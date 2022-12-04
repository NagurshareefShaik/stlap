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

@Data
@Entity
public class MobileUser {
	
	@Id
	private String mobileNumber;
	private String userName;
	private String email;
	private String otp;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name  = "role_id")
	private Role role;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name  = "branch_id")
	private BranchMaster branch;
	private int otpCount;
	private int isActive;
	private Date lastLoginTime;
	private int currenltyLoggedIn;

}
