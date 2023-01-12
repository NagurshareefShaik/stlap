package shfl.st.lap.nach.service;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.repo.NachRepo;

@Service
@AllArgsConstructor
public class NachService {
	
	private NachRepo nachRepo;
	
	public ResponseEntity<String> registerNach(Nach nach) {
		nach.setStatus("To be Registered");
		Nach naachEntity=nachRepo.save(nach);
		if(Objects.nonNull(naachEntity)){
			return ResponseEntity.ok().body("Nach Registration Successfull");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nach Registration Not Successfull");
		}
	}

}
