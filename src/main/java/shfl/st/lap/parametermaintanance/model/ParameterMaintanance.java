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
	@Column(name = "PARAMETER_ID")
	private int parameterId;
	@Column(name = "MINIMUM_DISBURSEMENT_AMOUNT")
	private int minimumDisbursementAmount;
	@Column(name = "PAYMENT_MODE")
	private String paymentMode;
	@Column(name = "MAXIMUM_ALLOWABLE_CASH_RECEIPT")
	private int maximumAllowableCashReceipt;
	@Column(name = "CHEQUE_STALE_DAYS")
	private int chequeStaleDays;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDateTime;

}
