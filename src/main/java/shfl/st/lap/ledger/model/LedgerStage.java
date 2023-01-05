package shfl.st.lap.ledger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_DC_FNMVT_DTL_STG")
public class LedgerStage extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ledger_key")
	private int ledgerKey;

	// foreign key
	@Column(name = "header_key")
	private Number headerKey;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "module_code")
	private Number moduleCode;

	// BRANCH or CORPORATE
	@Column(name = "acc_type")
	private String accountingType;

	@Column(name = "char_set")
	private String charset;

	@Column(name = "reference_num")
	private String referenceNum;

	@Column(name = "reference_type")
	private String referenceType;

	// generated value in backend
	@Column(name = "voucher_num")
	private Number voucherNum;

	@Column(name = "voucher_date")
	private Date voucherDate;

	@Column(name = "effective_date")
	private Date effectiveDate;

	@Column(name = "txn_account")
	private String txnAccount;

	@Column(name = "txn_code")
	private float txnCode;

	@Column(name = "txn_amt")
	private int txnAmt;

	@Column(name = "narration")
	private String narration;

	@Column(name = "module_id")
	private String moduleId;

}
