package shfl.st.lap.parametermaintanance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.util.DateConversion;
import shfl.st.lap.parametermaintanance.model.Parameter;
import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;
import shfl.st.lap.parametermaintanance.model.ParameterMaintananceResponse;
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
	ParameterMaintananceRepo parameterMaintananceRepo;

	@Autowired
	DateConversion dateConversion;

	/**
	 * insertorUpdateParameterData is used to insert/update parameterMaintanance Data
	 * 
	 * @param parameterMaintanance
	 * @return ResponseEntity<ParameterMaintanance>
	 */
	public ResponseEntity<ParameterMaintananceResponse> insertorUpdateParameterData(
			ParameterMaintanance parameterMaintanance) {
		ParameterMaintanance maintanance = parameterMaintananceRepo.save(parameterMaintanance);

		if (Objects.nonNull(maintanance)) {
			ParameterMaintananceResponse response = convertToResponse(maintanance);
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	/**
	 * getParameterData is used to get all the ParameterMaintanance Data
	 * 
	 * @return ResponseEntity<List<ParameterMaintanance>>
	 */
	public ResponseEntity<List<ParameterMaintananceResponse>> getParameterData() {
		List<ParameterMaintanance> parameterMaintanancesList = parameterMaintananceRepo.findAll();
		List<ParameterMaintananceResponse> parameterMaintananceResponseList = new ArrayList<>();
		parameterMaintanancesList.stream().forEach(maintanance -> {
			ParameterMaintananceResponse response = convertToResponse(maintanance);
			parameterMaintananceResponseList.add(response);
		});
		return ResponseEntity.ok().body(parameterMaintananceResponseList);
	}
	
	public ResponseEntity<Map<String,Object>> getParameterList() {
		Map<String,Object> parameterMap=new HashMap<>();
		List<ParameterMaintanance> parameterMaintanancesList = parameterMaintananceRepo.findAll();
		parameterMaintanancesList.stream().forEach(maintanance -> {
			ParameterMaintananceResponse response = convertToResponse(maintanance);
			parameterMap.put(response.getParamName(), response);
		});
		return ResponseEntity.ok().body(parameterMap);
	}
	

	/**
	 * getParameterById used to get the parameter data based on parameterId
	 * 
	 * @param parameter
	 * @return ParameterMaintanance
	 */
	public ResponseEntity<ParameterMaintananceResponse> getParameterById(Parameter parameter) {
		Optional<ParameterMaintanance> parameterMaintanance = parameterMaintananceRepo
				.findById(parameter.getParameterId());
		if (Objects.nonNull(parameterMaintanance)) {
			return ResponseEntity.ok().body(convertToResponse(parameterMaintanance.get()));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	public ParameterMaintananceResponse convertToResponse(ParameterMaintanance parameterMaintanance) {
		ParameterMaintananceResponse response = new ParameterMaintananceResponse();
		response.setParamDataType(parameterMaintanance.getParamDataType());
		response.setParamId(parameterMaintanance.getParamId());
		response.setModule(parameterMaintanance.getModule());
		response.setParamEffEndDate(dateConversion.convertDateTimeToDate(parameterMaintanance.getParamEffEndDate()));
		response.setParamEffStartDate(dateConversion.convertDateTimeToDate(parameterMaintanance.getParamEffStartDate()));
		response.setParamName(parameterMaintanance.getParamName());
		response.setParamValue((parameterMaintanance.getParamDataType().equals("Date")
				? dateConversion.convertStringToDate(parameterMaintanance.getParamValue())
				: parameterMaintanance.getParamValue()));
		return response;

	}

}
