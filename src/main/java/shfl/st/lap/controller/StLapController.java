package shfl.st.lap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.model.AppData;

@RestController
public class StLapController {
	
	@GetMapping("/getApplicationData")
	public String getApplicationData(@RequestBody AppData appData) {
		return "Application Data";
		
	}
	@PostMapping("/nachSave")
	public String saveNachDetails() {
		
		return null;
	}
	@PostMapping("/downloadNach/{mandateId}")
	public String downloadNach() {
		
		return null;
	}
	@GetMapping("/getDashboardData")
	public String getDashboardData() {
		return "Application Data";
		
	}
	

}