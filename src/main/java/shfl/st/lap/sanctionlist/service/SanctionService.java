package shfl.st.lap.sanctionlist.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.sanctionlist.model.SanctionList;
import shfl.st.lap.sanctionlist.repo.SanctionRepo;

@Service
public class SanctionService {
	
	@Autowired
	SanctionRepo sanctionRepo;
	
	public ResponseEntity<String> insertSanctionData(SanctionList sanctionList){
		SanctionList sanctionData=sanctionRepo.save(sanctionList);
		if (Objects.nonNull(sanctionData)) {
			return ResponseEntity.ok().body("SanctionData Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SanctionData Not Created");
		}
		
	}

	public ResponseEntity<List<SanctionList>> getSanctionData() {
		List<SanctionList> sanctionDataList=sanctionRepo.findAll();
		return ResponseEntity.ok(sanctionDataList);
	}
	
	

}
