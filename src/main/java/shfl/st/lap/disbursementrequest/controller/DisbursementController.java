package shfl.st.lap.disbursementrequest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shfl.st.lap.disbursementrequest.model.CustomerDisbNumber;
import shfl.st.lap.disbursementrequest.model.DisbursementModel;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.service.DisbursementService;

@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
	
	@Autowired
	DisbursementService disbursementService;
	
	@PostMapping("/insertDisbursement")
	private ResponseEntity<DisbursementModel> insertDisbursement(@RequestBody DisbursementModel disbursementModel) {
		return disbursementService.insertDisbursementData(disbursementModel);
	}
	
	@PostMapping("/getDisbursementData")
	private ResponseEntity<DisbursementModel> getDisbursementData(@RequestBody CustomerDisbNumber customerDisbNumber) {
		return disbursementService.getDisbursementData(customerDisbNumber);
	}
	
	@PostMapping("/updateDisbursement")
	private ResponseEntity<DisbursementModel> updateDisbursement(@RequestBody DisbursementModel disbursementModel) {
		return disbursementService.updateDisbursementData(disbursementModel);
	}
	
	@GetMapping("/getAllDisbursementData")
	private ResponseEntity<List<DisbursementRequest>> getAllDisbursementData() {
		return disbursementService.getAllDisbursementData();
	}

}