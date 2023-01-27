package shfl.st.lap.frequency.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.frequency.model.FrequencyType;
import shfl.st.lap.frequency.repo.FrequencyTypeRepo;

@Service
@AllArgsConstructor
public class FrequencyTypeService {

	private FrequencyTypeRepo frequencyTypeRepo;

	public ResponseEntity<String> insertFrequency(List<FrequencyType> frequencyTypeList) {
		List<FrequencyType> frequencyTypeResponse = frequencyTypeRepo.saveAll(frequencyTypeList);
		if (Objects.nonNull(frequencyTypeResponse)) {
			return ResponseEntity.ok().body("Frequency Type Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Frequency Type not Created Successfully");
		}
	}
	
	public ResponseEntity<List<FrequencyType>> getAllFrequencyTypes() {
		List<FrequencyType> frequencyTypeList = frequencyTypeRepo.findAll();
		if (Objects.nonNull(frequencyTypeList)) {
			return ResponseEntity.ok(frequencyTypeList);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
	}
	
	public void deleteAll() {
		frequencyTypeRepo.deleteAll();
	}

}
