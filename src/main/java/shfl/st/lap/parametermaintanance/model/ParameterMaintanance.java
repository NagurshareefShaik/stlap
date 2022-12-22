package shfl.st.lap.parametermaintanance.model;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_PARAMETER_MAINT")
public class ParameterMaintanance {

	@Id
	@GeneratedValue
	@Column(name = "parameter_id")
	private int parameterId;
	
	@Column(name = "parameter_name")
	private String parameterName;
	
	@Column(name = "parameter_value")
	private String parameterValue;
	
	@Column(name = "parameter_data_type")
	private String parameterDataType;
	
	@Column(name = "parameter_eff_start_dt")
	private Date parameterEffStartDt;
	
	@Column(name = "parameter_eff_end_dt")
	private Date parameterEffEndDt;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	private LocalDateTime modifiedDateTime;

}
