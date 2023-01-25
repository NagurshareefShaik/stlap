package shfl.st.lap.repaymentschedule.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AmortResposnseModel {

	private String applicationNum;

	private long totalPrincipalAmount;

	private long totalInterest;

	private String customerName;

	private int tenure;

	private long emiAmount;

	private int sanctionAmount;

	private String loanType;

	private String frequency;

	private Date mandateValidity;

	private List<AmortModel> amortModelList;

}