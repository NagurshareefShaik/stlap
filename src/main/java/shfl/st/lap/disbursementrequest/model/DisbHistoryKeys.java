package shfl.st.lap.disbursementrequest.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DisbHistoryKeys implements Serializable{
	   private String applicationNumber;
	   private int transactionId;
	}