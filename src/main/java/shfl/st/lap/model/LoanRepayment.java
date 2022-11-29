package shfl.st.lap.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRepayment {

	private int sno;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date duedate;
	private int instlamt;
	private int principal;
	private int interest;
	private int outstandamt;

}
