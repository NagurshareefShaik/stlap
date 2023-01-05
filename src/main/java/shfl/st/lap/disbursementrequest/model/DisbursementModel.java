package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class DisbursementModel {

	private Number disbHeaderKey;
	private Number transactionKey;
	private String applicationNum;
	private String branch;
	private String applicantName;
	private Number earlierDisbAmt;
	private Number disbAmt;
	private int disbNum;
	private float rateOfInterest;
	private Number totalDisbAmt;
	private Date dateOfDisb;
	private Number billDay;
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
	private Number disbEmiAmt;
	private Number totalDeductionAmt;

}
