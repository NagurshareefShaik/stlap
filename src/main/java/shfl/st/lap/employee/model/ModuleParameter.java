package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_MOD_PAR")
public class ModuleParameter {
	
	@Id
	@Column(name = "mod_param_id")
	private int modParamId;
	
	@Column(name = "mod_param_name")
	private String modParamName;
	
	@Column(name = "mod_param_value")
	private String modParamValue;
	
	

}
