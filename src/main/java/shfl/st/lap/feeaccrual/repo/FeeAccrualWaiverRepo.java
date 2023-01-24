package shfl.st.lap.feeaccrual.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.feeaccrual.model.AdditionalFeesDescription;
import shfl.st.lap.feeaccrual.model.AdditionalFeesDescriptionPkey;

public interface FeeAccrualWaiverRepo extends JpaRepository<AdditionalFeesDescription,AdditionalFeesDescriptionPkey>{

	List<AdditionalFeesDescription> findByApplicationNumber(String applicationNumber);
	
	AdditionalFeesDescription findByApplicationNumberAndFeeDescription(String applicationNumber, String description);

	
}