package shfl.st.lap.loscustomer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_CUST_DEP_BANK_DTL")
public class CustomerDepandantBankDetails {
	
	@Id
	@Column(name = "bank_acc_number")
	private int bankAccountNumber;
	
	@Column(name = "acc_holdr_name")
	private String accHoldrName;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_branch_name")
	private String bankBranchName;
	
	@Column(name = "bank_acc_type")
	private String bankAccountType;
	
	@Column(name = "micr_code")
	private String micrCode;
	
	@Column(name = "ifsc_code")
	private String ifscCode;

}
