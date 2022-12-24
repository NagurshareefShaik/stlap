package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_EMP_TYPE")
public class EmployeeType {

	@Id
	@Column(name = "employee_type")
	private String employeeType;

	@Column(name = "module_id")
	private String moduleId;

}
