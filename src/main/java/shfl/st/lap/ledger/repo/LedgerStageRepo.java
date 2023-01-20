package shfl.st.lap.ledger.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shfl.st.lap.ledger.model.LedgerStage;

@Repository
public interface LedgerStageRepo extends JpaRepository<LedgerStage, Integer>{

	List<LedgerStage> findByHeaderKey(int disbHeaderKey);
	
	@Query("SELECT coalesce(max(ls.voucherNum), 0) FROM LedgerStage ls")
	int getMaxVoucherNumber();

	@Query("SELECT coalesce(max(ls.voucherNum), 0) FROM LedgerStage ls where ls.headerKey=:disbHdrKey")
	int getMaxVoucherNumberByHdrKey(int disbHdrKey);

}
