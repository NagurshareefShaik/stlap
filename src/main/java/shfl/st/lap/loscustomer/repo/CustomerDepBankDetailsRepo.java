package shfl.st.lap.loscustomer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.loscustomer.model.CustomerDepBankKeys;
import shfl.st.lap.loscustomer.model.CustomerDepandantBankDetails;

public interface CustomerDepBankDetailsRepo extends JpaRepository<CustomerDepandantBankDetails, CustomerDepBankKeys> {

	List<CustomerDepandantBankDetails> findByApplicationNum(String appNum);
}
