package shfl.st.lap.loscustomer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shfl.st.lap.loscustomer.model.CustomerAppNumber;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.service.LosCustomerService;

@RestController
@RequestMapping("/losCustomer")
public class LosCustomerController {
	
	@Autowired
	LosCustomerService losCustomerService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertSanctionData(@RequestBody LosCustomer losCustomer){
		return losCustomerService.insertCustomerData(losCustomer);
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity<List<LosCustomer>> getCustomerData(){
		return losCustomerService.getCustomerData();
	}
	
	@PostMapping("/getCustomerDataByAppNum")
	public ResponseEntity<LosCustomer> getCustomerDataByAppnum(@RequestBody CustomerAppNumber customerAppNumber){
		return losCustomerService.getCustomerDataByAppNum(customerAppNumber.getApplicationNumber());
	}

}
