package shfl.st.lap.reports.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.reports.service.ReportService;


@RestController
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@GetMapping("/generateReport")
	public ResponseEntity<byte[]> GenerateCustomerReport() throws Exception {
		System.out.println("genereate report method started COntroller");
		return reportService.generateCustomerReport();
		
	}
	
	@PostMapping("/generateRepaySchedule")
	public ResponseEntity<byte[]> generateRepaySchedule(@RequestBody Map<String,String> appMap) throws Exception {
		return reportService.generateRepaySchedule(appMap);

	}

}
