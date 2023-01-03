package shfl.st.lap.feeaccrual.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shfl.st.lap.feeaccrual.model.AdditionalFees;

public interface AdditionalFeesRepo extends JpaRepository<AdditionalFees, String>{


//	@Query( value = "select reference_no from  stlapnew.st_tb_lms_addtnl_fees orderby reference_no desc",nativeQuery = true)
	List<AdditionalFees> findByOrderByReferenceNoDesc();

//	AdditionalFees findByApplicationNumberAndReferenceNo(String applicationNumber, Integer referenceNumber);

	AdditionalFees findByApplicationNumberAndReferenceNo(String applicationNumber, Integer referenceNumber);

	List<AdditionalFees> findByApplicationNumberAndFeeTypeOrderByReferenceNoDesc(String applicationNumber,String type);

}