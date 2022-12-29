package shfl.st.lap.feeaccrual.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.feeaccrual.model.AdditionalFees;

public interface AdditionalFeesRepo extends JpaRepository<AdditionalFees, String> {

	AdditionalFees findByApplicationNumber(String applicationNumber);

}