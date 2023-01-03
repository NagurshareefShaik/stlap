package shfl.st.lap.dashboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.dashboard.service.StlapDashboardService;
import shfl.st.lap.feeaccrual.service.FeeAccrualWaiverService;



@RestController
@RequestMapping("/dashboard")
public class StlapDashboardController {
	
	@Autowired
	StlapDashboardService stlapDashBoardService;
	
	@PostMapping("/getDashboardData")
	public ResponseEntity<Map<String,Object>> getDashBoardData(@RequestBody Map<String,Object> datamap) {
		return stlapDashBoardService.getDashBoardData(datamap);
	}
	
	
	
}