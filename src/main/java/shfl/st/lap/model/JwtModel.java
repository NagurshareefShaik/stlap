package shfl.st.lap.model;

import java.sql.Date;

import lombok.Data;

@Data
public class JwtModel {
	
	private String jwToken;
	private String userId;
	private Date lastLoginTime;

}
