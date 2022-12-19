package shfl.st.lap.parametermaintanance.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.authentication.util.LoggedUserData;
import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;
import shfl.st.lap.parametermaintanance.repo.ParameterMaintananceRepo;

@Service
public class ParameterMaintananceService {
	
	@Autowired
	LoggedUserData loggedUserData;
	
	@Autowired
	ParameterMaintananceRepo parameterMaintananceRepo;
	
	public ResponseEntity<String> insertParameterData(ParameterMaintanance parameterMaintanance) {
		parameterMaintanance.setCreatedBy(loggedUserData.getUserName());
		parameterMaintanance.setCreatedDateTime(new Date());
		ParameterMaintanance maintanance=parameterMaintananceRepo.save(parameterMaintanance);
		if(Objects.nonNull(maintanance)){
			return ResponseEntity.ok().body("Parameter Created Successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameter Not Created");
		}
	}

	public ResponseEntity<List<ParameterMaintanance>> getParameterData() {
		List<ParameterMaintanance> parameterMaintanancesList=parameterMaintananceRepo.findAll();
		return ResponseEntity.ok().body(parameterMaintanancesList);
	}

	public ResponseEntity<String> updateParameterData(ParameterMaintanance parameterMaintanance) {
		parameterMaintanance.setModifiedBy(loggedUserData.getUserName());
		parameterMaintanance.setModifiedDateTime(new Date());
		ParameterMaintanance maintanance=parameterMaintananceRepo.save(parameterMaintanance);
		if(Objects.nonNull(maintanance)){
			return ResponseEntity.ok().body("Parameter Updated Successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameter Not Updated");
		}
	}

}
