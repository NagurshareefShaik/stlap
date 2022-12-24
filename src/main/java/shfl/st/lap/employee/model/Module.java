package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_MOD")
public class Module {

	@Id
	@Column(name = "mod_id")
	private String modId;

	@Column(name = "mod_name")
	private String modName;
	
	@Column(name = "editable")
	private boolean editable;

	// foreign key
	// one to many mapping
	@Column(name = "mod_param_id")
	private int modParamId;

}
