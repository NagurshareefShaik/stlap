package shfl.st.lap.parametermaintanance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.parametermaintanance.model.Parameter;
import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;
import shfl.st.lap.parametermaintanance.service.ParameterMaintananceService;

@RestController
@RequestMapping("/parameter")
public class ParameterMaintananceController {
	
	@Autowired
	ParameterMaintananceService parameterMaintananceService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertParameterData(@RequestBody ParameterMaintanance parameterMaintanance) {
		return parameterMaintananceService.insertParameterData(parameterMaintanance);
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateParameterData(@RequestBody ParameterMaintanance parameterMaintanance) {
		return parameterMaintananceService.updateParameterData(parameterMaintanance);
		
	}
	
	@GetMapping("/getAllParameterData")
	public ResponseEntity<List<ParameterMaintanance>> getParameterData() {
		return parameterMaintananceService.getParameterData();
		
	}
	
	@PostMapping("/getParameterById")
	public ResponseEntity<ParameterMaintanance> getParameterById(@RequestBody Parameter parameter) {
		return parameterMaintananceService.getParameterById(parameter);
	}
	

}
