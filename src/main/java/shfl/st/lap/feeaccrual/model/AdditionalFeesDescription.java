package shfl.st.lap.feeaccrual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.loscustomer.model.CustomerDepBankKeys;


@Data
@Entity
@Table(name = "ST_TB_LMS_ADDTNL_FEE_DESC")
@IdClass(AdditionalFeesDescriptionPkey.class)
public class AdditionalFeesDescription {
	
	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Id
	@Column(name = "fee_description")
	private String feeDescription;

	@Column(name = "receivable")
	private Integer receivable;

	@Column(name = "received")
	private Integer received;

	@Column(name = "earlier_waiver")
	private Integer earlierWaiver;
	
	@Column(name = "additional_accrual")
	private Integer additionalAccrual;

	@Column(name = "outst_amount")
	private Integer outstAmount;

	@Column(name = "deductions")
	private Integer deductions;
	

}
