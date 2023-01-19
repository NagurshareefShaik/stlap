package shfl.st.lap.shflApis.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.shflApis.model.ShflApis;
import shfl.st.lap.shflApis.repo.ShflApiRepo;

@Service
public class ShflApiService {
	
	@Autowired
	ShflApiRepo shflApiRepo;
	
	public ResponseEntity<String> insertApi(ShflApis apis){
		ShflApis shflApis =shflApiRepo.save(apis);
		if (Objects.nonNull(shflApis)) {
			return ResponseEntity.ok().body("Shfl Api Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shfl Api Not Created");
		}
	}
	
	public ResponseEntity<ShflApis> getApiByCode(String apiCode){
		Optional<ShflApis> shflApiData= shflApiRepo.findById(apiCode);
		if(shflApiData.isPresent()) {
			return ResponseEntity.ok(shflApiData.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShflApis());
		
	}

}
