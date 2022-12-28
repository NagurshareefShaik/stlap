package shfl.st.lap.parametermaintanance.service;

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
	 * insertorUpdateParameterData is used to insert/update parameterMaintanance Data
	 * 
	 * @param parameterMaintanance
	 * @return ResponseEntity<ParameterMaintanance>
	 */
	public ResponseEntity<ParameterMaintanance> insertorUpdateParameterData(ParameterMaintanance parameterMaintanance) {
		ParameterMaintanance maintanance = parameterMaintananceRepo.save(parameterMaintanance);
		if (Objects.nonNull(maintanance)) {
			return ResponseEntity.ok().body(maintanance);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
