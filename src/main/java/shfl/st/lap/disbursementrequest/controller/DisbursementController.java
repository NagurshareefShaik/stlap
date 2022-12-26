package shfl.st.lap.disbursementrequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.service.DisbursementService;

@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
	
	@Autowired
	DisbursementService disbursementService;
	
	@PostMapping("/insert")
	private ResponseEntity<String> insertDisbursement(@RequestBody DisbursementRequest disbursementRequest) {
		return disbursementService.insertDisbursementData(disbursementRequest);
	}

}
