package shfl.st.lap.authentication.model;

import java.sql.Date;

import lombok.Data;

@Data
public class JwtModel {
	
	private String jwToken;
	private String userId;
	private String lastLoginTime;

}
