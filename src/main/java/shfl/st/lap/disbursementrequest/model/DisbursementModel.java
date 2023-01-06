package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class DisbursementModel {

	private int disbHeaderKey;
	private int transactionKey;
	private String applicationNum;
	private String branch;
	private String applicantName;
	private int earlierDisbAmt;
	private int disbAmt;
	private int disbNum;
	private float rateOfInterest;
	private int totalDisbAmt;
	private Date dateOfDisb;
	private int billDay;
	private String billingDate;
	private String emiCommDate;
	private String firstEmiDueDate;
	private Date effectiveDate;
	private String requestStatus;
	private String paymentMode;
	private String remarks;
	private boolean editLock;
	// CREATE or UPDATE OR CANCEL OR APPROVED
	private String screenMode;
	private List<DisbursementFavour> disbursementFavours;
	private int disbEmiAmt;
	private int totalDeductionAmt;
	private String approvalRemarks;

}
