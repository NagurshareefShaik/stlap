package shfl.st.lap.customer.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_CUS_BANK_MSTR")
public class CustomerBankMaster {

	@Id
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "customer_name")
	private String customerName;
	
	//foreign key
	//one to many relation
	@Column(name = "bank_acc_number")
	private String bankAccNumber;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	private LocalDateTime modidiedDateTime;
}
