package shfl.st.lap.parametermaintanance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_PARAM_MAINT")
public class ParameterMaintanance extends Auditable{

	@Id
	@GeneratedValue
	@Column(name = "param_id")
	private int paramId;

	@Column(name = "param_name")
	private String paramName;

	@Column(name = "param_value")
	private String paramValue;
	
	@Column(name = "module")
	private String module;

	@Column(name = "param_data_type")
	private String paramDataType;

	@Column(name = "param_eff_start_dt")
	private Date paramEffStartDt;

	@Column(name = "param_eff_end_dt")
	private Date paramEffEndDt;

}
