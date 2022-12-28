package shfl.st.lap.disbursementrequest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.disbursementrequest.model.DisbursementHistory;

@Repository
public interface DisbursementHistoryRepo extends JpaRepository<DisbursementHistory, Integer>{

}
