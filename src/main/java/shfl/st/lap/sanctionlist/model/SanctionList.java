package shfl.st.lap.sanctionlist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_SANC_LIST")
public class SanctionList {
	
	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "customer_type")
	private String customerType;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "application_date")
	private Date applicationDate;
	
	@Column(name = "approved_amount")
	private float approvedAmount;
	
	@Column(name = "status")
	private String status;
	
}
