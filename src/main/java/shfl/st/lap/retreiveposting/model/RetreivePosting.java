package shfl.st.lap.retreiveposting.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_NACH_RETREIVE_POSTING")
public class RetreivePosting {
	
	@Id
	@Column(name = "application_num")
	private String applicationNum;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "sponsor_bank")
	private String sponsorBank;
	
	@Column(name = "mode_of_operation")
	private String modeOfOperation;
	
	@Column(name = "voucher_date")
	private Date voucherDate;
	
	@Column(name = "posting_date")
	private Date postingDate;
	
	@Column(name = "due_date")
	private Date dueDate;
	
	@Column(name = "emi_amt")
	private float emiAmt;
	
	@Column(name = "principal_amt")
	private float principalAmt;
	
	@Column(name = "interest_amt")
	private float interestAmt;
	
	@Column(name = "outstanding_amt")
	private float outstandingAmt;
	
}
