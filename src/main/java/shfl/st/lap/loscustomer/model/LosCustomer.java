package shfl.st.lap.loscustomer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_TEMP_CUSTOMER")
public class LosCustomer extends Auditable {

	@Id
	@Column(name = "application_num")
	private String applicationNum;

	@Column(name = "request_num")
	private String requestNum;

	@Column(name = "branch")
	private String branch;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "disb_num")
	private int disbNum;

	@Column(name = "co_applicant_name")
	private String coApplicantName;

	@Column(name = "customer_type")
	private String customerType;

	@Column(name = "rate_of_interest")
	private float rateOfInterest;

	@Column(name = "application_date")
	private Date applicationDate;

	@Column(name = "loan_amt")
	private float loanAmt;

	@Column(name = "sanction_amt")
	private float sanctionAmt;

	@Column(name = "effective_date")
	private Date effectiveDate;

	@Column(name = "los_status")
	private String losStatus;

	@Column(name = "legal_app_date")
	private Date legalApprovedDate;

	@Column(name = "tech_app_date")
	private Date technicalApprovedDate;

	@Column(name = "cr_app_date")
	private Date creditApprovedDate;

	@Column(name = "rcu_app_date")
	private Date rcuApprovedDate;

	@Column(name = "legal_deviation_status")
	private String legalDeviationStatus;

	@Column(name = "techinical_deviation_status")
	private String techinicalDeviationStatus;

	@Column(name = "cr_deviation_status")
	private String creditDeviationStatus;

	@Column(name = "memo_paid")
	private float memoPaid;

	@Column(name = "memo_waived")
	private float memoWaived;

	@Column(name = "memo_outsanding")
	private float memoOutsanding;

	@Column(name = "memo_deduction")
	private float memoDeduction;
	
	@Column(name = "tenure")
	private int tenure;

	@Column(name = "mobile_number")
	private int mobileNumber;
	
	@Column(name = "email_id")
	private String emailId;

}
