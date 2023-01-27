package shfl.st.lap.debittype.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.debittype.model.DebitType;
import shfl.st.lap.debittype.repo.DebitTypeRepo;

@Service
@AllArgsConstructor
public class DebitTypeService {

	private DebitTypeRepo debitTypeRepo;

	public ResponseEntity<String> insertDebit(List<DebitType> debitTypeList) {
		List<DebitType> debitTypeResponse = debitTypeRepo.saveAll(debitTypeList);
		if (Objects.nonNull(debitTypeResponse)) {
			return ResponseEntity.ok().body("debit Type Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("debit Type not Created Successfully");
		}
	}
	
	public ResponseEntity<List<DebitType>> getAlldebitTypes() {
		List<DebitType> debitTypeList = debitTypeRepo.findAll();
		if (Objects.nonNull(debitTypeList)) {
			return ResponseEntity.ok(debitTypeList);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
	}
	
	public void deleteAll() {
		debitTypeRepo.deleteAll();
	}

}
