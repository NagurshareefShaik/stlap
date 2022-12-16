package shfl.st.lap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MobileUser {

	@Id
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "otp")
	private String otp;
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "branch_id")
	private int branchId;
	@Column(name = "otp_count")
	private int otpCount;
	@Column(name = "is_active")
	private int isActive;
	@Column(name = "last_login_time")
	private String lastLoginTime;
	@Column(name = "currently_logged_in")
	private int currenltyLoggedIn;

}
