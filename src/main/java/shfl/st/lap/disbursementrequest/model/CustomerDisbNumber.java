package shfl.st.lap.disbursementrequest.model;

import lombok.Data;

@Data
public class CustomerDisbNumber {
	
	private String disbRequestId;
	//VIEW or MODIFY or CANCEL
	private String screenMode;

}
