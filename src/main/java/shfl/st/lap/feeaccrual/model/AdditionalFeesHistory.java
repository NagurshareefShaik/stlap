package shfl.st.lap.feeaccrual.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_ADDTNL_FEES_HISTORY")
@IdClass(AdditionalFeesPkey.class)
public class AdditionalFeesHistory {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	@Id
	@Column(name = "fee_type")
	private String feeType;
	
	@Id
	@Column(name = "reference_no")
	private Integer referenceNo;
	
	@Id
	@Column(name = "modified_content")
	private String modifiedContent;
	
	@Id
	@Column(name = "modified_date_time")
	private LocalDateTime modifiedDateTime;
	
	@Column(name = "reference_date")
	private String referenceDate;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_value")
	private String modifiedValue;

	
}
