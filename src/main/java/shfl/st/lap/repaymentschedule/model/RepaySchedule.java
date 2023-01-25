package shfl.st.lap.repaymentschedule.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_REPAY_SCHEDULE")
@IdClass(RepaymentScheduleKeys.class)
public class RepaySchedule {

	@Id
	@Column(name = "application_num")
	private String applicationNum;

	@Id
	@Column(name = "emi_date")
	private Date emiDate;

	@Column(name = "year")
	private int year;

	@Column(name = "month")
	private int month;

	@Column(name = "interest_amt")
	private int interestAmt;

	@Column(name = "principal_amt")
	private int principalAmt;

	private float rateOfInterest;

	@Column(name = "outstanding_amt")
	private int outstandingAmt;

	@Column(name = "opening_balance")
	private int openingBalance;

	@Column(name = "closing_balance")
	private int closingBalance;

	@Column(name = "disb_amt")
	private int disbAmt;

	@Column(name = "repay_amt")
	private int repayAmt;

}
