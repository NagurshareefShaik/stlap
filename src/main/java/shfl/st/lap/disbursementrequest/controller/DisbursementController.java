package shfl.st.lap.disbursementrequest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shfl.st.lap.disbursementrequest.model.CustomerDisbNumber;
import shfl.st.lap.disbursementrequest.model.DisbAppModel;
import shfl.st.lap.disbursementrequest.model.DisbPagenationModel;
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
	public ResponseEntity<DisbursementModel> insertDisbursement(@RequestBody DisbursementModel disbursementModel) {
		return disbursementService.insertDisbursementData(disbursementModel);
	}

	@PostMapping("/getDisbursementData")
	public ResponseEntity<DisbursementModel> getDisbursementData(@RequestBody CustomerDisbNumber customerDisbNumber) {
		return disbursementService.getDisbursementData(customerDisbNumber);
	}

	@PostMapping("/updateDisbursement")
	public ResponseEntity<DisbursementModel> updateDisbursement(@RequestBody DisbursementModel disbursementModel) {
		return disbursementService.updateDisbursementData(disbursementModel);
	}

	@PostMapping("/getAllDisbursementData")
	public ResponseEntity<Page<DisbursementRequest>> getAllDisbursementData(@RequestBody DisbPagenationModel disbPagenationModel) {
		return disbursementService.getAllDisbursementData(disbPagenationModel);
	}

	@PostMapping("/registerBillingDay")
	public String registerBillingDay(@RequestBody DisbursementBillingDay disbursementBillingDay) {
		return disbursementService.registerBillingDay(disbursementBillingDay);
	}

	@GetMapping("/getAllDisbursementBillingDayData")
	public ResponseEntity<List<DisbursementBillingDay>> getAllDisbursementBillingDayData() {
		return disbursementService.getAllDisbursementBillingDayData();
	}

	@PostMapping("/searchAllDisbBranchData")
	public ResponseEntity<List<DisbursementRequest>> searchAllDisbBranchData(
			@RequestBody DisbursementBranch disbursementBranch) {
		return disbursementService.searchAllDisbBranchData(disbursementBranch.getBranch());
	}

	@PostMapping("/editLockUpdate")
	public ResponseEntity<DisbursementRequest> editLockUpdate(@RequestBody CustomerDisbNumber customerDisbNumber) {
		return disbursementService.editLockUpdate(customerDisbNumber);
	}
	
	@PostMapping("/getFirstDisbByAppNum")
	public ResponseEntity<List<DisbursementRequest>> getFirstDisbByAppNum(@RequestBody DisbAppModel disbAppModel) {
        return disbursementService.getFirstDisbByAppNum(disbAppModel);
    }

}
