package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ST_TB_LMS_EMP_ACCESS")
public class EmployeeAccess {
	
	@Id
	@Column(name = "access_id")
	private int accessId;
	
	//second key
	@Column(name = "screen_name")
	private String screenName;
	
	@Column(name = "editable")
	private boolean editable;
	

}
