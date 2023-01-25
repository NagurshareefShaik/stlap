package shfl.st.lap.repaymentschedule.model;

import java.util.Date;

import lombok.Data;

@Data
public class AmortModel {
	
	private int monthNo;
	
	private Date duedate;
	
	private long openingAmt;
	
	private long monthlyAmt;
	
	private long principal;
	
	private long interest;
	
	private long closingAmt;
	
	private long repayAmt;

}
