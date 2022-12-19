package shfl.st.lap.parametermaintanance.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.authentication.util.LoggedUserData;
import shfl.st.lap.parametermaintanance.model.Parameter;
import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;
import shfl.st.lap.parametermaintanance.repo.ParameterMaintananceRepo;

/**
 * 
 * @author nagubash
 * @since 12.22
 *
 */
@Service
public class ParameterMaintananceService {

	@Autowired
	LoggedUserData loggedUserData;

	@Autowired
	ParameterMaintananceRepo parameterMaintananceRepo;

	/**
	 * insertParameterData is used to insert parameterMaintanance Data
	 * 
	 * @param parameterMaintanance
	 * @return
	 */
	public ResponseEntity<String> insertParameterData(ParameterMaintanance parameterMaintanance) {
		parameterMaintanance.setCreatedBy(loggedUserData.getUserName());
		parameterMaintanance.setCreatedDateTime(new Date());
		ParameterMaintanance maintanance = parameterMaintananceRepo.save(parameterMaintanance);
		if (Objects.nonNull(maintanance)) {
			return ResponseEntity.ok().body("Parameter Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameter Not Created");
		}
	}

	/**
	 * getParameterData is used to get all the ParameterMaintanance Data
	 * 
	 * @return ResponseEntity<List<ParameterMaintanance>>
	 */
	public ResponseEntity<List<ParameterMaintanance>> getParameterData() {
		List<ParameterMaintanance> parameterMaintanancesList = parameterMaintananceRepo.findAll();
		return ResponseEntity.ok().body(parameterMaintanancesList);
	}

	/**
	 * updateParameterData is used to update the ParameterMaintanance data
	 * 
	 * @param parameterMaintanance
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> updateParameterData(ParameterMaintanance parameterMaintanance) {
		parameterMaintanance.setModifiedBy(loggedUserData.getUserName());
		parameterMaintanance.setModifiedDateTime(new Date());
		ParameterMaintanance maintanance = parameterMaintananceRepo.save(parameterMaintanance);
		if (Objects.nonNull(maintanance)) {
			return ResponseEntity.ok().body("Parameter Updated Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameter Not Updated");
		}
	}

	/**
	 * getParameterById used to get the parameter data based on parameterId
	 * 
	 * @param parameter
	 * @return ParameterMaintanance
	 */
	public ResponseEntity<ParameterMaintanance> getParameterById(Parameter parameter) {
		Optional<ParameterMaintanance> parameterMaintanance = parameterMaintananceRepo
				.findById(parameter.getParameterId());
		if (Objects.nonNull(parameterMaintanance)) {
			return ResponseEntity.ok().body(parameterMaintanance.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ParameterMaintanance());
		}
	}

}
