package shfl.st.lap.shflApis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.shflApis.model.ShflApis;
import shfl.st.lap.shflApis.model.ShflApisModel;
import shfl.st.lap.shflApis.service.ShflApiService;

@RestController
@RequestMapping("/api")
public class ShflApiController {
	
	@Autowired
	ShflApiService shflApiService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertApi(@RequestBody ShflApis shflApis){
		return shflApiService.insertApi(shflApis);
		
	}
	
	@PostMapping("/getApiUrlByCode")
	public ResponseEntity<ShflApis> getApiUrlByCode(@RequestBody Map<String,String> apiCodeMap){
		return shflApiService.getApiByCode(apiCodeMap.get("code"));
		
	}
	
	@PostMapping("/getShflApiUrlByCode")
	public ResponseEntity<ShflApis> getShflApiUrlByCode(@RequestBody ShflApisModel shflApisModel) {
		return shflApiService.getShflApiUrlByCode(shflApisModel);
	}

}
