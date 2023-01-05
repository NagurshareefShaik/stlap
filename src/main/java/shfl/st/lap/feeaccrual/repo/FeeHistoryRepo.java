package shfl.st.lap.feeaccrual.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.feeaccrual.model.AdditionalFeesHistory;

public interface FeeHistoryRepo extends JpaRepository<AdditionalFeesHistory, String>{

	List<AdditionalFeesHistory> findByApplicationNumberAndFeeTypeOrderByModifiedDateTimeDesc(String applicationNumber, String feeType);

}
