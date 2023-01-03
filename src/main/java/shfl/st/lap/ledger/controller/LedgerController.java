package shfl.st.lap.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.ledger.model.LedgerModel;
import shfl.st.lap.ledger.service.LedgerService;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
	
	@Autowired
	LedgerService ledgerService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertLedgerData(@RequestBody LedgerModel ledgerModel){
		return ledgerService.insertLedgerData(ledgerModel);
	}

}
