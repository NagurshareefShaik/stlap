package shfl.st.lap.employee.model;

import java.util.List;

import lombok.Data;

@Data
public class DisbursmentProcess {
	
	private String applicantName;
	private String loanRequestDate;
	private int totalDisbursmentAmt;
	private int numberOfDisbursment;
	private int currentDisbursment;
	private String effectiveRate;
	private String proposalType;
	private String sanctionDate;
	private String fileNumber;
	private String dateOfDisbursment;
	private String paymentMode;
	private String chequeMode;
	private String chequePrintAt;
	private String entityName;
	private String favourName;
	private String accountNumber;
	private String debitAccountDetail;
	private String ifscCode;
	private List<DisbursmentCurrent> disbursmentCurrent;

}
