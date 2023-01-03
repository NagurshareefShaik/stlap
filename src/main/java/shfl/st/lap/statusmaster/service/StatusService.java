package shfl.st.lap.statusmaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;
import shfl.st.lap.parametermaintanance.model.ParameterMaintananceResponse;
import shfl.st.lap.statusmaster.model.Status;
import shfl.st.lap.statusmaster.repo.StatusRepo;

@Service
public class StatusService {
	
	@Autowired
	StatusRepo statusRepo;
	
	public ResponseEntity<String> inserStatus(Status status) {
		Status statusData = statusRepo.save(status);

		if (Objects.nonNull(statusData)) {
			return ResponseEntity.ok().body("Status created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status not created");
		}
	}

	public ResponseEntity<List<String>> getAllStatusData() {
		List<Status> statusList = statusRepo.findAll();
		return ResponseEntity.ok(statusList.stream().map(Status::getStatusName).collect(Collectors.toList()));
	}


}
