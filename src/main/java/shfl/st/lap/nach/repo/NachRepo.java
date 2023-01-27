package shfl.st.lap.nach.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import shfl.st.lap.nach.model.Nach;

public interface NachRepo extends JpaRepository<Nach, String>,JpaSpecificationExecutor<Nach> {
	
	Nach findByApplicationNum(String appNum);
	
	Nach findByBranchAndApplicationNum(String branch,String applicationNum);

	List<Nach> findByBranchAndStatus(String branch, String status);

	List<Nach> findByStatusIn(Set<String> statusList);

	List<Nach> findByStatus(String status);
	
	
}
