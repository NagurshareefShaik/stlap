package shfl.st.lap.nach.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.model.NachResponseModel;
import shfl.st.lap.nach.service.NachService;

@RestController
@RequestMapping("/nach")
@AllArgsConstructor
public class NachController {
	
	private NachService nachService;
	
	@PostMapping("/register")
	public ResponseEntity<Nach> nachRegister(@RequestBody Nach nach){
		return nachService.registerNach(nach);
	}
	
	@PostMapping("/getDisbRequestedData")
	public ResponseEntity<NachResponseModel> getRequestedDisbData(@RequestBody Map<String, String> map){
		return nachService.getRequestedDisbData(map);
	}
	
	@PostMapping("/getNachData")
	public ResponseEntity<NachResponseModel> getNachData(@RequestBody Map<String,String> map){
		return nachService.getNachDetails(map);
	}
	
	@PostMapping("/getNachDataByStatus")
	public ResponseEntity<List<NachResponseModel>> getNachVerification(@RequestBody Map<String,String> branchMap){
		return nachService.getNachDataByStatus(branchMap);
	}
	
	@PostMapping("/nachStatusChange")
	public ResponseEntity<NachResponseModel> nachStatusChange(@RequestBody Map<String,String> map){
		return nachService.nachStatusChange(map);
	}
	

}
