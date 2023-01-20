package shfl.st.lap.nach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import shfl.st.lap.nach.model.Nach;

public interface NachRepo extends JpaRepository<Nach, String>,JpaSpecificationExecutor<Nach> {
	
	Nach findByApplicationNum(String appNum);
	
	Nach findByBranchAndApplicationNum(String branch,String applicationNum);
	
	
}