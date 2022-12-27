package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_REQ")
public class DisbursementRequest extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "disb_request_id")
	private int disbRequestId;

	// foreign key
	@Column(name = "application_number", nullable = false)
	private String applicationNumber;

	// foreign key
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id", nullable = false)
	private int transactionId;

	@Column(name = "earlier_disb_amt")
	private float earlierDisbAmt;

	@Column(name = "disb_amt")
	private float disbAmt;
	
	@Column(name="disb_no")
	private int disbNo;

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
	
	@Column(name = "first_emi_due_date")
	private Date firstEmiDueDate;
	
	@Column(name = "effective_date")
	private Date effectiveDate;

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

}
