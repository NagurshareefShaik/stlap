package shfl.st.lap.nach.model;

import java.util.Date;

import lombok.Data;

@Data
public class NachResponseModel {
	
	private String mandateNum;
	
	private String branch;
	
	private String applicationNum;
	
	private String applicationCustomer;
	
	private String bankName;
	
	private String branchName;
	
	private String micr;
	
	private String accountType;
	
	private String bankAccountNum;
	
	private String bankAccHolderName;
	
	private String ifscCode;
	
	private int customerId;
	
	private int emiAmt;
	
	private int nachAmt;
	
	private int mandateAmt;
	
	private String frequency;
	
	private String debitType;
	
	private Date fbd;
	
	private Date mandateStartDate;
	
	private Date firstNachBillingDate;
	
	private Date mandateValidity;
	
	private Date mandateEndDate;
	
	private int maximumAmt;
	
	private int customerMobileNum;
	
	private String customerEmailId;
	
	private String status;
	
	private float loanAmount;
	
	private String repay;
	
	private String repayApplication;
	
	private float disbursementAmount;
	
	private Date sanctionDate;
	
	private String draweePlace;
	
	private int umrnNumber;
	
}
