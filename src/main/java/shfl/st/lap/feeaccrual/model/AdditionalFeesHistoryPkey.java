package shfl.st.lap.feeaccrual.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AdditionalFeesHistoryPkey implements Serializable{
	private String applicationNumber;
	private String feeType;
	private Integer referenceNo;
	private String modifiedContent;
	private LocalDateTime modifiedDateTime;
}
