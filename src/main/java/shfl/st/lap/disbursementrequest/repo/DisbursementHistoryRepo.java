package shfl.st.lap.disbursementrequest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.disbursementrequest.model.DisbursementHistory;

public interface DisbursementHistoryRepo extends JpaRepository<DisbursementHistory, Integer>{

}
