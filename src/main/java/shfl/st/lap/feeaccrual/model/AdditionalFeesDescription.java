package shfl.st.lap.feeaccrual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "ST_TB_LOS_ADDTNL_FEE_DESC")
public class AdditionalFeesDescription {
	
	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	//second key
	@Column(name = "fee_description")
	private String feeDescription;

	@Column(name = "receivable")
	private float receivable;

	@Column(name = "received")
	private float received;

	@Column(name = "earlier_waiver")
	private float earlierWaiver;

	@Column(name = "outst_amount")
	private float outstAmount;

	@Column(name = "deductions")
	private float deductions;

}
