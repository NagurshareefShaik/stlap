package shfl.st.lap.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetails {

	private String customerName;
	private String loanNumber;
	private int tenure;
	private int totalInstl;
	private String currency;
	private String loanType;
	private int amountFinanced;
	private String frequency;
	private List<LoanRepayment> loanRepayment;

}
