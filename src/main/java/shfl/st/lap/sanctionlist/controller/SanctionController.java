package shfl.st.lap.sanctionlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.sanctionlist.model.SanctionList;
import shfl.st.lap.sanctionlist.service.SanctionService;

@RestController
@RequestMapping("/sanction")
public class SanctionController {
	
	@Autowired
	SanctionService sanctionService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertSanctionData(@RequestBody SanctionList sanctionList){
		return sanctionService.insertSanctionData(sanctionList);
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity<List<SanctionList>> getSanctionData(){
		return sanctionService.getSanctionData();
	}

}
