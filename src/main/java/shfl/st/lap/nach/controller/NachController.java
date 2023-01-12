package shfl.st.lap.nach.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.service.NachService;

@RestController
@RequestMapping("/nach")
@AllArgsConstructor
public class NachController {
	
	private NachService nachService;
	
	@PostMapping("/register")
	public ResponseEntity<String> nachRegister(@RequestBody Nach nach){
		return nachService.registerNach(nach);
	}
	

}
