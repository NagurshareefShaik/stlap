package shfl.st.lap.feeaccrual.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_ACC_WAV_FEES_DESC_AMT")
public class AccrualWaiverFeesDescAmt {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	//second key
	@Column(name = "fee_description")
	private String feeDescription;

	@Column(name = "addtnl_fee_amount")
	private float addtnlFeeAmount;
	
	@Column(name = "oustanding_amount")
	private float oustandingAmount;
	
	@Column(name = "addtnl_fee_type")
	private String addtnlFeeType;
	
	

}
