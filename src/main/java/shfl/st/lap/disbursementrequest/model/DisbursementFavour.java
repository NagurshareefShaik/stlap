package shfl.st.lap.disbursementrequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_FAV")
public class DisbursementFavour {
	
	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	// secondary key
	@Column(name = "bank_acc_number", nullable = false)
	private int bankAccNumber;
	
	@Column(name = "dist_no")
	private int distNo;
	
	@Column(name = "disbamount")
	private float disbAmount;
	
}
