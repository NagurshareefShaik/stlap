package shfl.st.lap.ledger.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import shfl.st.lap.ledger.model.LedgerModel;
import shfl.st.lap.ledger.repo.LedgerRepo;

@Service
public class LedgerService {
	
	@Autowired
	LedgerRepo ledgerRepo;
	
	public ResponseEntity<String> insertLedgerData(LedgerModel ledgerModel){
		LedgerModel model=ledgerRepo.save(ledgerModel);
		if (Objects.nonNull(model)) {
			return ResponseEntity.ok().body("Ledger Data Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ledger Data Data Not Created");
		}
		
	}

}
