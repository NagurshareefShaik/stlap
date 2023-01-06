package shfl.st.lap.disbursementrequest.model;

import lombok.Data;

@Data
public class CustomerDisbNumber {

	private int disbHeaderKey;
	// VIEW or MODIFY or CANCEL
	private String screenMode;

}
