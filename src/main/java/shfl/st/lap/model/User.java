package shfl.st.lap.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {
	@Id
	private String userId;
	private String email;
	private String password;
	private String role;
}
