package shfl.st.lap.nach.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ST_TB_LMS_NACH")
public class Nach extends Auditable {

	@Id
	@Column(name = "mandate_num")
	private String mandateNum;

	@Column(name = "bank_account_num")
	private String bankAccountNum;
	
	@Column(name = "branch")
	private String branch;
	
	// foreign key
	@Column(name = "application_num")
	private String applicationNum;

	@Column(name = "nach_amt")
	private int nachAmt;

	@Column(name = "mandate_amt")
	private int mandateAmt;

	@Column(name = "frequency")
	private int frequency;

	@Column(name = "debit_type")
	private String debitType;

	@Column(name = "fbd")
	private Date fbd;

	@Column(name = "mandate_start_date")
	private Date mandateStartDate;

	@Column(name = "first_nach_billing_date")
	private Date firstNachBillingDate;

	@Column(name = "mandate_validity")
	private Date mandateValidity;

	@Column(name = "maximum_amt")
	private int maximumAmt;

	@Column(name = "status")
	private String status;
	
	@Column(name = "umrnNumber")
	private int umrnNumber;

	@Column(name = "edit_lock")
	private boolean editLock;

}