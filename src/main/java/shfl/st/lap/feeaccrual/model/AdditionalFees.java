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
@Table(name = "ST_TB_LMS_ADDTNL_FEES")
@IdClass(AdditionalFeesPkey.class)
public class AdditionalFees {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	@Id
	@Column(name = "fee_type")
	private String feeType;
	
	@Id
	@Column(name = "reference_no")
	private Integer referenceNo;
	
	@Column(name = "reference_date")
	private String referenceDate;

	
	@Column(name = "reason")
	private String reason;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "edit_lock")
	private boolean editLock;

}
