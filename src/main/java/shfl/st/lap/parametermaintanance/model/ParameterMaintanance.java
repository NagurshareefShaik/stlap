package shfl.st.lap.parametermaintanance.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PARAMETER_MAINTANANCE")
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
	
	@Column(name = "parameter_effective_start_date")
	private Date parameterEffectiveStartDate;
	
	@Column(name = "parameter_effective_end_date")
	private Date parameterEffectiveEndDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date_time")
	private Date createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	private Date modifiedDateTime;

}
