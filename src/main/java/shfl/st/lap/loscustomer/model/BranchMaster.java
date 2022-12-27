package shfl.st.lap.loscustomer.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_TEMP_BRANCH_MSTR")
public class BranchMaster {

	@Id
	@Column(name = "branch_id")
	private int branchId;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "branch_location")
	private String branchLocation;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date_time")
	private LocalDateTime modidiedDateTime;
}
