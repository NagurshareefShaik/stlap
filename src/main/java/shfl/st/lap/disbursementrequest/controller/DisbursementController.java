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
import shfl.st.lap.disbursementrequest.model.DisbAppModel;
import shfl.st.lap.disbursementrequest.model.DisbursementBillingDay;
import shfl.st.lap.disbursementrequest.model.DisbursementBranch;
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

	@PostMapping("/registerBillingDay")
	private String registerBillingDay(@RequestBody DisbursementBillingDay disbursementBillingDay) {
		return disbursementService.registerBillingDay(disbursementBillingDay);
	}

	@GetMapping("/getAllDisbursementBillingDayData")
	private ResponseEntity<List<DisbursementBillingDay>> getAllDisbursementBillingDayData() {
		return disbursementService.getAllDisbursementBillingDayData();
	}

	@PostMapping("/searchAllDisbBranchData")
	private ResponseEntity<List<DisbursementRequest>> searchAllDisbBranchData(
			@RequestBody DisbursementBranch disbursementBranch) {
		return disbursementService.searchAllDisbBranchData(disbursementBranch.getBranch());
	}

	@PostMapping("/editLockUpdate")
	private ResponseEntity<DisbursementRequest> editLockUpdate(@RequestBody CustomerDisbNumber customerDisbNumber) {
		return disbursementService.editLockUpdate(customerDisbNumber);
	}
	
	@PostMapping("/getFirstDisbByAppNum")
    private ResponseEntity<List<DisbursementRequest>> getFirstDisbByAppNum(@RequestBody DisbAppModel disbAppModel) {
        return disbursementService.getFirstDisbByAppNum(disbAppModel);
    }

}
