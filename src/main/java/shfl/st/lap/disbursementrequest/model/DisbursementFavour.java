package shfl.st.lap.disbursementrequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_FAV")
public class DisbursementFavour extends Auditable{
	
	@Id
	@Column(name = "bank_acc_number", nullable = false)
	private int bankAccNumber;
	
	//foreign key
	@Column(name = "disb_request_id")
	private int disbRequestId;
		
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "dist_no")
	private int distNo;
	
	@Column(name = "disbamount")
	private float disbAmount;
	
}
