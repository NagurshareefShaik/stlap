package shfl.st.lap.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MobileUser {
	
	@Id
	private String mobileNumber;
	private String userName;
	private String email;
	private String otp;
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name  = "role_id")
	private String role;
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name  = "branch_id")
	private String branch;
	private int otpCount;
	private int isActive;
	private Date lastLoginTime;
	private int currenltyLoggedIn;

}
