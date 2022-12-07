package shfl.st.lap.model;

import lombok.Data;

@Data
public class DisbursmentCurrent {
	private int id;
	private int amount;
	private String paymentMode;
	private String emiType;
	private String entityName;
	private String accountNumber;
	private String ifscCode;
}
