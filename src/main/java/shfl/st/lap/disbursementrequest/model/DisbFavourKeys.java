package shfl.st.lap.disbursementrequest.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DisbFavourKeys implements Serializable {
	private String applicationNumber;
	private int bankAccNumber;
}