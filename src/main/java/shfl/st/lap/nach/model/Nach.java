package shfl.st.lap.nach.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Nach {
	
	@Id
	@Column(name = "mandate_num")
	private String mandateNum;
	
	@Column(name = "branch")
	private String branch;
	
	//foreign key
	@Column(name = "application_num")
	private String applicationNum;
	
	//foreign key
	@Column(name = "header_key")
	private String headerKey;
	
	@Column(name = "application_customer")
	private String applicationCustomer;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "")
	private String micr;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "bank_account_num")
	private int bankAccountNum;
	
	@Column(name = "bank_acc_holder_name")
	private String bankAccHolderName;
	
	@Column(name = "emi_amt")
	private int emiAmt;
	
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
	
	@Column(name = "customer_mobile_num")
	private int customerMobileNum;
	
	@Column(name = "customer_email_id")
	private String customerEmailId;
	
	@Column(name = "status")
	private String status;
	
}
