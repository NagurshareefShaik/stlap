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
@Table(name = "ST_TB_LMS_DISB_HISTORY")
public class DisbursementHistory extends Auditable{
	
	@Id
	@Column(name = "disb_hist_id")
	private int disbHistId;
	
	//foreign key
	@Column(name = "disb_request_id")
	private int disbRequestId;
	
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "applicant_name")
	private String applicantName;
	
	@Column(name = "earlier_disb_amt")
	private float earlierDisbAmt;
	
	@Column(name = "disb_amt")
	private float disbAmt;
	
	@Column(name="rate_of_interest")
	private float rateOfInterest;

	@Column(name = "total_disb_amt")
	private float totalDisbAmt;
	
	@Column(name = "date_of_disb")
	private Date dateOfDisb;

	@Column(name = "billing_day")
	private String billingDay;

	@Column(name = "billing_date")
	private String billingDate;
	
	@Column(name = "emi_comm_date")
	private Date emiCommDate;
	
	@Column(name = "first_emi_due_date")
	private Date firstEmiDueDate;

	@Column(name = "request_status")
	private String requestStatus;
	
	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "shfl_bank")
	private String shflBank;
	
	@Column(name = "remarks")
	private String remarks;
	
}
