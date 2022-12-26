package shfl.st.lap.disbursementrequest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.disbursementrequest.model.DisbursementRequest;


@Repository
public interface DisbursementRepo extends JpaRepository<DisbursementRequest, String>{

}
