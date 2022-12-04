package shfl.st.lap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "branch")
public class BranchMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int branchId;
	private String branchName;

}
