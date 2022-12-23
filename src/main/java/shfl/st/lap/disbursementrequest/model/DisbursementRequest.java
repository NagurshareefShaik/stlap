package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_REQ")
public class DisbursementRequest {

	@Id
	@Column(name = "disb_request_id")
	private String disbRequestId;

	// foreign key
	@Column(name = "application_number", nullable = false)
	private String applicationNumber;

	// foreign key
	//auto generate
	@Column(name = "transaction_id", nullable = false)
	private int transactionId;

	@Column(name = "earlier_disb_amt")
	private float earlierDisbAmt;

	@Column(name = "disb_amt")
	private float disbAmt;

	@Column(name = "total_disb_amt")
	private float totalDisbAmt;

	@Column(name = "date_of_disb")
	private Date dateOfDisb;

	@Column(name = "billing_day")
	private Date billingDay;

	@Column(name = "billing_date")
	private Date billingDate;

	@Column(name = "emi_comm_date")
	private Date emiCommDate;

	@Column(name = "request_status")
	private String requestStatus;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "shfl_bank")
	private String shflBank;

	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "edit_lock")
	private boolean editLock;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;

}
