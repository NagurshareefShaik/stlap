package shfl.st.lap.nach.model;

import java.sql.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class NachResponseModel {
	
	private String mandateNum;
	
	private String branch;
	
	private String applicationNum;
	
	private String headerKey;
	
	private String applicationCustomer;
	
	private String bankName;
	
	private String branchName;
	
	private String micr;
	
	private String accountType;
	
	private String bankAccountNum;
	
	private String bankAccHolderName;
	
	private int emiAmt;
	
	private int nachAmt;
	
	private int mandateAmt;
	
	private int frequency;
	
	private String debitType;
	
	private Date fbd;
	
	private Date mandateStartDate;
	
	private Date firstNachBillingDate;
	
	private Date mandateValidity;
	
	private int maximumAmt;
	
	private int customerMobileNum;
	
	private String customerEmailId;
	
	@Column(name = "status")
	private String status;
	
}
