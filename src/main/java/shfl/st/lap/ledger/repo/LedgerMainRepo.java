package shfl.st.lap.ledger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.ledger.model.LedgerMain;

@Repository
public interface LedgerMainRepo extends JpaRepository<LedgerMain, Integer>{

}
