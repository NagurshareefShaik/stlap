package shfl.st.lap.nach.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@AllArgsConstructor
@Table(name = "ST_TB_LMS_NACH")
public class Nach extends Auditable {

	@Id
	@Column(name = "mandate_num")
	private String mandateNum;

	// foreign key
	@Column(name = "application_num")
	private String applicationNum;

	// foreign key
	@Column(name = "header_key")
	private String headerKey;

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

	@Column(name = "edit_lock")
	private boolean editLock;

}