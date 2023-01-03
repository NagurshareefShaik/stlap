package shfl.st.lap.ledger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_LEDGER")
public class LedgerModel extends Auditable{

	@Id
	@GeneratedValue
	@Column(name = "ledger_id")
	private int ledgerId;

	// foreign key 
	@Column(name = "disb_request_id")
	private int disbRequestId;

	@Column(name = "application_number")
	private String applicationNumber;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "module_code")
	private String moduleCode;

	@Column(name = "header_key")
	private String headerKey;

	@Column(name = "charset")
	private String charset;

	@Column(name = "reff_number")
	private String reffNumber;
	
	@Column(name = "reff_type")
	private String reffType;

	@Column(name = "voucher_number")
	private String voucherNumber;

	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "voucher_date")
	private Date voucherDate;

	@Column(name = "voucher_eff_st_date")
	private Date voucherEffStDate;

	@Column(name = "trans_acc")
	private String transAcc;

	@Column(name = "tran_amt")
	private float tranAmt;
	
	@Column(name = "trans_code")
	private int transCode;

	@Column(name = "remarks")
	private String remarks;

}
