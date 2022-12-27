package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_HISTORY")
public class DisbursementHistory extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "disb_history_id")
	private int disbHistoryId;
	
	@Column(name = "transaction_id")
	private int transactionId;
	
	@Column(name = "application_number")
	private String applicationNumber;
	
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
