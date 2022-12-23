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
@Table(name = "ST_TB_LMS_ADDTNL_FEES_DESC_AMT")
public class AdditionalFeesDescAmt {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;

	// key
	@Column(name = "fee_description")
	private String feeDescription;
	
	// key
	@Column(name = "addtnl_fee_type")
	private String addtnlFeeType;

	@Column(name = "addtnl_fee_amount")
	private float addtnlFeeAmount;
	
	@Column(name = "oustanding_amount")
	private float oustandingAmount;
	
	
	
	

}
