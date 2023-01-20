package shfl.st.lap.nach.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.model.NachFilterParams;
import shfl.st.lap.nach.model.NachResponseModel;
import shfl.st.lap.nach.model.PreVerificationModel;
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
	
	@GetMapping("/getAllRequestedDisbData")
	public ResponseEntity<List<NachResponseModel>> getRequestedDisbData(){
		return nachService.getRequestedDisbData();
	}
	
	
	@PostMapping("/getNachData")
	public ResponseEntity<NachResponseModel> getNachData(@RequestBody Map<String,String> map){
		return nachService.getNachDetails(map);
	}
	
	@PostMapping("/getNachVerificationData")
	public ResponseEntity<List<NachResponseModel>> getNachVerification(@RequestBody NachFilterParams filterParams){
		return nachService.getNachVerification(filterParams);
	}
	
	@PostMapping("/nachStatusChange")
	public ResponseEntity<NachResponseModel> nachStatusChange(@RequestBody Map<String,String> map){
		return nachService.nachStatusChange(map);
	}
	
	@PostMapping("/preVerification")
	public ResponseEntity<NachResponseModel> preVerification(@RequestBody PreVerificationModel preVerificationModel){
		return nachService.preVerification(preVerificationModel);
	}
	
	

}
