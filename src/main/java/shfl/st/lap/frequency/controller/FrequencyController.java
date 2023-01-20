package shfl.st.lap.frequency.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.frequency.model.FrequencyType;
import shfl.st.lap.frequency.service.FrequencyTypeService;

@RestController
@AllArgsConstructor
@RequestMapping("/frequency")
public class FrequencyController {
	
	private FrequencyTypeService frequencyTypeService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertFrequencyType(@RequestBody List<FrequencyType> frequencyTypeList) {
		return frequencyTypeService.insertFrequency(frequencyTypeList);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<FrequencyType>> getAllFrequencyData() {
		return frequencyTypeService.getAllFrequencyTypes();
	}
	

}
