package shfl.st.lap.disbursementrequest.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementRepo;

@Service
public class DisbursementService {

	
	@Autowired
	DisbursementRepo disbursementRepo;
	
	public ResponseEntity<String> insertDisbursementData(DisbursementRequest disbursementRequest) {
		DisbursementRequest disbursementRequestData = disbursementRepo.save(disbursementRequest);
		if(Objects.nonNull(disbursementRequestData)) {
			return ResponseEntity.ok().body("Disbursement Request Inserted Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Disbursement Requset not initialized");
		}
	}

}
