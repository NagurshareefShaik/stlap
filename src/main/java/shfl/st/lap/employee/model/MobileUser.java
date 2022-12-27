package shfl.st.lap.employee.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_TEMP_MOBILE_USR")
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

	// foreign key
	@Column(name = "role_id")
	private int roleId;

	// foreign key
	@Column(name = "branch_id")
	private int branchId;

	// foreign key
	@Column(name = "employee_type")
	private String employeeType;

	@Column(name = "otp_count")
	private int otpCount;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;

}
