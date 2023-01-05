package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_REQ_HDR")
public class DisbursementRequest extends Auditable {

	@Id
	@Column(name = "disb_hdr_key")
	private Number disbHeaderKey;

	@Column(name = "trans_key")
	private Number transactionKey;

	@Column(name = "application_num", nullable = false)
	private String applicationNum;

	@Column(name = "branch")
	private String branch;

	@Column(name = "applicant_name")
	private String applicantName;

	@Column(name = "earlier_disb_amt")
	private Number earlierDisbAmt;

	@Column(name = "disb_amt")
	private Number disbAmt;

	@Column(name = "disb_num")
	private int disbNum;

	@Column(name = "rate_of_interest")
	private float rateOfInterest;

	@Column(name = "total_disb_amt")
	private Number totalDisbAmt;

	@Column(name = "date_of_disb")
	private Date dateOfDisb;

	@Column(name = "bill_day")
	private Number billDay;

	@Column(name = "billing_date")
	private String billingDate;

	@Column(name = "emi_comm_date")
	private String emiCommDate;

	@Column(name = "first_emi_due_date")
	private String firstEmiDueDate;

	@Column(name = "effective_date")
	private Date effectiveDate;

	@Column(name = "request_status")
	private String requestStatus;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "edit_lock")
	private boolean editLock;

	@Column(name = "module_id")
	private String moduleId;

	@Column(name = "disb_emi_amt")
	private Number disbEmiAmt;

	@Column(name = "total_ded_amt")
	private Number totalDeductionAmt;

}
