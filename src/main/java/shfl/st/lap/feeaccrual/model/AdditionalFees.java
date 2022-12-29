package shfl.st.lap.feeaccrual.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_ADDTNL_FEES")
public class AdditionalFees {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	// secondary key
	@Column(name = "reference_no")
	private String referenceNo;

	@Column(name = "reference_date")
	private String referenceDate;

	@Column(name = "addtnl_fee_ded_type")
	private String addtnlFeeDedType;

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

	@Column(name = "modified_date_time")
	private LocalDateTime modidiedDateTime;

}
