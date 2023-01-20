package shfl.st.lap.disbursementrequest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import shfl.st.lap.disbursementrequest.model.DisbursementRequest;

@Repository
public interface DisbursementRequestRepo extends JpaRepository<DisbursementRequest, Integer>,JpaSpecificationExecutor<DisbursementRequest> {

	List<DisbursementRequest> findByBranch(String branch);

	List<DisbursementRequest> findByApplicationNum(String applicationNum);

	List<DisbursementRequest> findByEditLock(Boolean editLock);
	
	List<DisbursementRequest> findByRequestStatus(String requestStatus);

}
