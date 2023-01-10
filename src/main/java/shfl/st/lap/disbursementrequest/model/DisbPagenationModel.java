package shfl.st.lap.disbursementrequest.model;

import lombok.Data;

@Data
public class DisbPagenationModel {
	
	private String applicationNum;
	private String branch;
	private String applicantName;
	private String requestNumber;
	private String disbursementStatus;
	private String disbursementDate;
	private int offset;
	private int pageSize;

}
