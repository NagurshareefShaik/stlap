package shfl.st.lap.repaymentschedule.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.repaymentschedule.model.AmortResposnseModel;
import shfl.st.lap.repaymentschedule.service.RepaymentService;

@RestController
@AllArgsConstructor
@RequestMapping("/repayment")
public class RepaymentController {
	
	private RepaymentService repaymentService;
	
	@PostMapping("/getEmiDataByAppNum")
	public ResponseEntity<AmortResposnseModel> getAmortCalculation(@RequestBody Map<String,String> map){
		AmortResposnseModel amortResponse=repaymentService.calculateRepaymentSchedule(map);
		return ResponseEntity.ok(amortResponse);
	}

}