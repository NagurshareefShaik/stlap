package shfl.st.lap.nach.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.nach.service.NachService;

@RestController
@RequestMapping("/enach")
@AllArgsConstructor
public class ENachController {
	
	private NachService nachService;
	
	@PostMapping("/enachDetails")
	public Map<String,Object> enachDetails(@RequestBody Map<String,String> nach){
		return nachService.enachDetails(nach);
	}
	@PostMapping("/getApplicants")
	public List<Map<String,Object>> getApplicants(@RequestBody Map<String,Object> nach){
		return nachService.getApplicants(nach);
	}

	

}
