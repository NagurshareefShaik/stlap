package shfl.st.lap.customer.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_CUSTOMER")
public class LosCustomer {
	
	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "request_number")
	private String requestNumber;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "co_applicant_name")
	private String coApplicantName;
	
	@Column(name = "customer_type")
	private String customerType;
	
	@Column(name = "rate_of_interest")
	private String rateOfInterest;
	
	@Column(name = "application_date")
	private Date applicationDate;
	
	@Column(name = "loan_amount")
	private float loanAmount;
	
	@Column(name = "sanction_amount")
	private float sanctionAmount;
	
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
	
	@Column(name = "mobile_umber")
	private int mobileNumber;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	private LocalDateTime modidiedDateTime;

}
