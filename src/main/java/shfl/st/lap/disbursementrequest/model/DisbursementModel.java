package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class DisbursementModel {
	
	private int disbRequestId;
	private String applicationNumber;
	private String branch;
	private String applicantName;
	private float earlierDisbAmt;
	private float disbAmt;
	private int disbNo;
	private float rateOfInterest;
	private float totalDisbAmt;
	private Date dateOfDisb;
	private String billingDay;
	private String billingDate;
	private String emiCommDate;
	private String firstEmiDueDate;
	private Date effectiveDate;
	private String requestStatus;
	private String paymentMode;
	private String shflBank;
	private String remarks;
	private boolean editLock;
	//CREATE or UPDATE
	private String screenMode;
	private List<DisbursementFavour> disbursementFavours;

}
