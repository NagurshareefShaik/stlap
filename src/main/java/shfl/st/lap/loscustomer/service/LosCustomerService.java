package shfl.st.lap.loscustomer.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;

@Service
public class LosCustomerService {
	
	@Autowired
	LosCustomerRepo losCustomerRepo;
	
	public ResponseEntity<String> insertCustomerData(LosCustomer losCustomer){
		LosCustomer losCustomerData=losCustomerRepo.save(losCustomer);
		if (Objects.nonNull(losCustomerData)) {
			return ResponseEntity.ok().body("Los Customer Data Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los Customer Data Not Created");
		}
		
	}

	public ResponseEntity<List<LosCustomer>> getCustomerData() {
		List<LosCustomer> losCustomerList=losCustomerRepo.findAll();
		return ResponseEntity.ok(losCustomerList);
	}
	
	public ResponseEntity<LosCustomer> getCustomerDataByAppNum(String appNum) {
		Optional<LosCustomer> losCustomer=losCustomerRepo.findById(appNum);
		if(losCustomer.isPresent()) {
			return ResponseEntity.ok(losCustomer.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new LosCustomer());
		
	}
	
	
	
	

}
