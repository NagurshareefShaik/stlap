package shfl.st.lap.debittype.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import shfl.st.lap.debittype.model.DebitType;
import shfl.st.lap.debittype.service.DebitTypeService;

@RestController
@AllArgsConstructor
@RequestMapping("/debit")
public class DebitTypeController {
	
	private DebitTypeService debitTypeService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertDebitType(@RequestBody List<DebitType> debitTypeList) {
		return debitTypeService.insertDebit(debitTypeList);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<DebitType>> getAlldebitData() {
		return debitTypeService.getAlldebitTypes();
	}
	

}
