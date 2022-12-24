package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_EMP_PAR")
public class EmployeeParameter {
	
	@Id
	@Column(name = "emp_param_id")
	private int empParamId;
	
	@Column(name = "emp_param_name")
	private String empParamName;
	
	@Column(name = "emp_param_value")
	private String empParamValue;
	
	

}
