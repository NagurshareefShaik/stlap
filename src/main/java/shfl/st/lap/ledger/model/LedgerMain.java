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
@Table(name = "ST_TB_LMS_LEDGER_MAIN")
public class LedgerMain extends Auditable{

	@Id
	@GeneratedValue
	@Column(name = "ledger_id")
	private int ledgerId;

	// foreign key 
	@Column(name = "disb_request_id")
	private String disbRequestId;

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

	@Column(name = "ref_no")
	private String refNo;
	
	@Column(name = "ref_type")
	private String refType;

	@Column(name = "voucher_no")
	private String voucherNo;

	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "voucher_date")
	private Date voucherDate;

	@Column(name = "eff_date")
	private Date effDate;

	@Column(name = "txn_account")
	private String txnAccount;

	@Column(name = "txn_code")
	private float txnCode;
	
	@Column(name = "txn_amount")
	private int txnAmount;

	@Column(name = "narration")
	private String narration;

}
