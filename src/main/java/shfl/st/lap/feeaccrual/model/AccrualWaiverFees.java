package shfl.st.lap.feeaccrual.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_ACCRUAL_WAIVER_FEES")
public class AccrualWaiverFees {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	//secondary key
	@Column(name = "reference_no")
	private String referenceNo;

	@Column(name = "reference_date")
	private Date referenceDate;

	@Column(name = "addtnl_fee_ded_type")
	private String addtnlFeeDedType;

	@Column(name = "addtnl_fee_ded_amount")
	private float addtnlFeeDedAmount;
	
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

	@Column(name = "modified_date_time")
	private LocalDateTime modidiedDateTime;

}
