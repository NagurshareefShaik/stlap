package shfl.st.lap.disbursementrequest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.disbursementrequest.model.DisbursementFavour;

@Repository
public interface DisbursementFavourRepo extends JpaRepository<DisbursementFavour, Integer> {

	List<DisbursementFavour> findByDisbHeaderKey(Integer disbHeaderKey);

}
