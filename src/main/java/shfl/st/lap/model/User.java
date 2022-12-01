package shfl.st.lap.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
	@Id
	private String employeeId;
	private String email;
	private String password;
	private String role;
}
