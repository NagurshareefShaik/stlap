package shfl.st.lap.loscustomer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.loscustomer.model.LosCustomer;

@Repository
public interface LosCustomerRepo extends JpaRepository<LosCustomer, String>{

	List<LosCustomer> findByBranch(String string);

	List<LosCustomer> findByapplicationNumAndCoApplicantName(String applicationNum, String applicatnName);

	List<LosCustomer> findByapplicationNum(String applicationNum);
	
}
