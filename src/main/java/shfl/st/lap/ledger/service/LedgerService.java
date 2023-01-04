package shfl.st.lap.ledger.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.ledger.model.LedgerMain;
import shfl.st.lap.ledger.repo.LedgerRepo;

@Service
public class LedgerService {
	
	@Autowired
	LedgerRepo ledgerRepo;
	
	public ResponseEntity<String> insertLedgerData(LedgerMain ledgerModel){
		LedgerMain model=ledgerRepo.save(ledgerModel);
		if (Objects.nonNull(model)) {
			return ResponseEntity.ok().body("Ledger Data Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ledger Data Data Not Created");
		}
		
	}

}
