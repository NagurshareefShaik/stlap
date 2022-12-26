package shfl.st.lap.loscustomer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.loscustomer.model.LosCustomer;

@Repository
public interface LosCustomerRepo extends JpaRepository<LosCustomer, String>{
	
}
