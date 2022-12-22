package shfl.st.lap.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LOS_BRANCH_MASTER")
public class BranchMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "branch_id")
	private int branchId;
	
	@Column(name = "branch_name")
	private String branchName;

}
