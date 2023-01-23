package shfl.st.lap.feeaccrual.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.feeaccrual.service.FeeAccrualWaiverService;



@RestController
@RequestMapping("/additionalfee")
public class FeeAccrualWaiverController {
	
	@Autowired
	FeeAccrualWaiverService feeAccrualWaiverService;
	
	@PostMapping("/saveFeeDetails")
	public ResponseEntity<String> saveFeeDetails(@RequestBody Map<String,Object> datamap) {
		return feeAccrualWaiverService.saveFeeDetails(datamap);
	}
	@PostMapping("/getFeeData")
	public ResponseEntity<Map<String,Object>>getFeeData(@RequestBody Map<String,Object> datamap){
		return feeAccrualWaiverService.getFeeData(datamap);
	}
	
	@PostMapping("/getHistoryData")
	public ResponseEntity<Map<String,Object>>getHistoryData(@RequestBody Map<String,Object> datamap){
		return feeAccrualWaiverService.getHistoryData(datamap);
	}
	@PostMapping("/getApplicationNumber")
	public ResponseEntity<List<Map<String,String>>>getApplicationNumber(@RequestBody Map<String,Object> datamap){
		return feeAccrualWaiverService.getApplicationNumber(datamap);
	}
	
	@PostMapping("/updateReceivedAmount")
	public ResponseEntity<String> updateReceivedAmount(@RequestBody Map<String, Object> dataMap) {
		return feeAccrualWaiverService.updateReceivedAmount(dataMap);
	}
}