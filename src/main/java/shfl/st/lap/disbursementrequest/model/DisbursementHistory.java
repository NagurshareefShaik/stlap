package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_HISTORY")
public class DisbursementHistory {
	
	@Id
	@Column(name = "tanscation_id")
	private int tanscationId;
	
	//second key
	//auto generate
	@Column(name = "disb_history_id")
	private int disbHistoryId;
	
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

	@Column(name = "request_status")
	private String requestStatus;
	
	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "shfl_bank")
	private String shflBank;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	

}
