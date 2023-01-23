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

	/**
	 * insert api method is used to insert all shfl api we used in the project
	 * 
	 * @param apis
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> insertApi(ShflApis apis) {
		ShflApis shflApis = shflApiRepo.save(apis);
		if (Objects.nonNull(shflApis)) {
			return ResponseEntity.ok().body("Shfl Api Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shfl Api Not Created");
		}
	}

	/**
	 * getShflApiUrlByCode method is used to fetch apiUrl by using apiCode
	 * 
	 * @param shflApisModel
	 * @return shflApisData
	 */
	public ResponseEntity<ShflApis> getShflApiUrlByCode(ShflApisModel shflApisModel) {
		Optional<ShflApis> shflApisData = shflApiRepo.findById(shflApisModel.getApiCode());
		setShflHistoryData(shflApisModel, shflApisData.get());
		if (shflApisData.isPresent()) {
			return ResponseEntity.ok(shflApisData.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ShflApis());
	}

	/**
	 * setShflHistoryData method is used to insert the history of api calls
	 * 
	 * @param shflApisModel
	 * @param shflApis
	 */
	private void setShflHistoryData(ShflApisModel shflApisModel, ShflApis shflApis) {
		ShflApiHistory shflApiHistoryEntity = new ShflApiHistory();
		shflApiHistoryEntity.setApiCode(shflApisModel.getApiCode());
		shflApiHistoryEntity.setApiUrl(shflApis.getApiUrl());
		shflApiHistoryEntity.setUser(shflApisModel.getUser());
		shflApiHistoryRepo.save(shflApiHistoryEntity);
	}

}
