package shfl.st.lap.shflApis.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.shflApis.model.ShflApiHistory;
import shfl.st.lap.shflApis.model.ShflApis;
import shfl.st.lap.shflApis.model.ShflApisModel;
import shfl.st.lap.shflApis.repo.ShflApiHistoryRepo;
import shfl.st.lap.shflApis.repo.ShflApiRepo;

@Service
public class ShflApiService {
	
	@Autowired
	ShflApiRepo shflApiRepo;
	
	@Autowired
	ShflApiHistoryRepo shflApiHistoryRepo;
	
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

	public ResponseEntity<ShflApis> getShflApiUrlByCode(ShflApisModel shflApisModel) {
		Optional<ShflApis> shflApisData = shflApiRepo.findById(shflApisModel.getApiCode());
		setShflHistoryData(shflApisModel, shflApisData.get());
		if(shflApisData.isPresent()) {
			return ResponseEntity.ok(shflApisData.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShflApis());
	}

	private void setShflHistoryData(ShflApisModel shflApisModel, ShflApis shflApis) {
		ShflApiHistory shflApiHistoryEntity = new ShflApiHistory();
		shflApiHistoryEntity.setApiCode(shflApisModel.getApiCode());
		shflApiHistoryEntity.setApiUrl(shflApis.getApiUrl());
		shflApiHistoryEntity.setUser(shflApisModel.getUser());
		shflApiHistoryRepo.save(shflApiHistoryEntity);
	}

}
