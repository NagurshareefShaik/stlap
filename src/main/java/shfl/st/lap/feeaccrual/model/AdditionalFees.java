package shfl.st.lap.feeaccrual.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ADDITIONAL_FEES")
public class AdditionalFees {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	@Column(name = "reference_no")
	private String referenceNo;
	@Column(name = "disbursement_branch")
	private String disbursementBranch;
	@Column(name = "reference_date")
	private Date referenceDate;
	@Column(name = "customer_data")
	private String customerData;
	@Column(name = "fee_description")
	private String feeDescription;
	@Column(name = "receivable")
	private int receivable;
	@Column(name = "received")
	private int received;
	@Column(name = "earlier_waiver")
	private int earlierWaiver;
	@Column(name = "outstanding_amount")
	private int outstandingAmount;
	@Column(name = "deductions")
	private int deductions;
	@Column(name = "reason")
	private String reason;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "additional_fee_deduction_type")
	private String additionalFeeDeductionType;
	@Column(name = "additional_fee_deduction_amount")
	private int additionalFeeDeductionAmount;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_date_time")
	private Date createdDateTime;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "modified_date_time")
	private Date modidiedDateTime;

}
