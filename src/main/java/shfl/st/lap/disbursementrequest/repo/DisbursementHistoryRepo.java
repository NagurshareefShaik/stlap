package shfl.st.lap.disbursementrequest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.disbursementrequest.model.DisbHistoryKeys;
import shfl.st.lap.disbursementrequest.model.DisbursementHistory;

public interface DisbursementHistoryRepo extends JpaRepository<DisbursementHistory, DisbHistoryKeys>{

}
