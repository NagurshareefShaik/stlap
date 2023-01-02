package shfl.st.lap.loscustomer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.loscustomer.model.CustomerDepandantBankDetails;
import shfl.st.lap.loscustomer.repo.CustomerDepBankDetailsRepo;

@Service
public class CustomerDepBankDetailsService {
	
	@Autowired
	CustomerDepBankDetailsRepo bankDetailsRepo;
	
	public ResponseEntity<String> insertCusBankDetails(CustomerDepandantBankDetails bankDetails){
		CustomerDepandantBankDetails losCustomerData=bankDetailsRepo.save(bankDetails);
		if (Objects.nonNull(losCustomerData)) {
			return ResponseEntity.ok().body("Customer Depandant Bank Details inserted Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Depandant Bank Details Not inserted");
		}
	}
	
	public ResponseEntity<List<CustomerDepandantBankDetails>> getCustBankDetailsByAppNum(String AppNumber){
		List<CustomerDepandantBankDetails> custBankDetailsList=bankDetailsRepo.findByApplicationNumber(AppNumber);
		if (Objects.nonNull(custBankDetailsList)) {
			return ResponseEntity.ok().body(custBankDetailsList);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
	}
	

}
