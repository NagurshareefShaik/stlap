package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DISBURSEMENT_REQUEST")
public class DisbursementRequest {

	@Id
	@GeneratedValue
	@Column(name = "disbursement_number")
	private int disbursementNumber;

	@Column(name = "request_number")
	private String requestNumber;

	@Column(name = "disbursement_branch")
	private String disbursementBranch;

	// foreign key
	@Column(name = "application_number", nullable = false)
	private String applicationNumber;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "rate_of_interest")
	private Float rateOfInterest;

	@Column(name = "loan_amount")
	private int loanAmount;

	@Column(name = "sanction_amount")
	private int sanctionAmount;

	@Column(name = "effective_date")
	private Date effectiveDate;

	@Column(name = "number_of_disbursement")
	private int numberOfDisbursement;

	@Column(name = "earlier_disbursement_amount")
	private int earlierDisbursementAmount;

	@Column(name = "current_disbursement_amount")
	private int currentDisbursementAmount;

	@Column(name = "total_disbursement_amount")
	private int totalDisbursementAmount;

	@Column(name = "date_of_disbursement")
	private Date dateOfDisbursement;

	@Column(name = "voucher_date")
	private Date voucherDate;

	@Column(name = "emi_commencement_date")
	private Date emiCommencementDate;

	@Column(name = "request_status")
	private String requestStatus;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_branch_name")
	private String bankBranchName;

	@Column(name = "bank_account_number")
	private int bankAccountNumber;

	@Column(name = "bank_account_type")
	private String bankAccountType;

	@Column(name = "shfl_bank")
	private String shflBank;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "memo_due")
	private double memoDue;

	@Column(name = "memo_paid")
	private double memoPaid;

	@Column(name = "memo_waived")
	private double memoWaived;

	@Column(name = "memo_outsanding")
	private double memoOutsanding;

	@Column(name = "memo_deduction")
	private double memoDeduction;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private Date createdDateTime;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date_time")
	private Date modidiedDateTime;

}
