package shfl.st.lap.statusmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.statusmaster.model.Status;
import shfl.st.lap.statusmaster.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertStatus(@RequestBody Status status){
		return statusService.inserStatus(status);
	}
	
	@GetMapping("/getAllStatuses")
	public ResponseEntity<List<String>> getAllStatusdata(){
		return statusService.getAllStatusData();
	}

}
