package shfl.st.lap.disbursementrequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_REQ_DTL")
public class DisbursementFavour extends Auditable {

	@Id
	@Column(name = "bank_acc_num", nullable = false)
	private int bankAccountNum;

	// foreign key
	@Column(name = "disb_hdr_key")
	private Number disbHeaderKey;

	@Column(name = "application_num")
	private String applicationNum;

	@Column(name = "disb_num")
	private int disbNum;

	@Column(name = "disb_amt")
	private Number disbAmt;

	// First disbursement empty once disb approved utr number shown in bank detail
	// grid
	@Column(name = "utr_num")
	private Number utrNum;

}
