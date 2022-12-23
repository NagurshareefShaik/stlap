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
@Table(name = "ST_TB_LMS_PARAM_MAINT")
public class ParameterMaintanance {

	@Id
	@GeneratedValue
	@Column(name = "param_id")
	private int paramId;

	@Column(name = "param_name")
	private String paramName;

	@Column(name = "param_value")
	private String paramValue;

	@Column(name = "param_data_type")
	private String paramDataType;

	@Column(name = "param_eff_start_dt")
	private Date paramEffStartDt;

	@Column(name = "param_eff_end_dt")
	private Date paramEffEndDt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date_time")
	private LocalDateTime modifiedDateTime;

}
