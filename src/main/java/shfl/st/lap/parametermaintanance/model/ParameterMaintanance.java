package shfl.st.lap.parametermaintanance.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "PARAMETER_MAINTANANCE")
public class ParameterMaintanance {

	@Id
	@GeneratedValue
	@Column(name = "parameter_id")
	private int parameterId;
	
	@Column(name = "minimum_disbursement_amount")
	private int minimumDisbursementAmount;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "maximum_allowable_cash_receipt")
	private int maximumAllowableCashReceipt;
	
	@Column(name = "cheque_stale_days")
	private int chequeStaleDays;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDateTime;

}
