package shfl.st.lap.nach.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.loscustomer.model.CustomerDepandantBankDetails;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.CustomerDepBankDetailsRepo;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;
import shfl.st.lap.nach.model.MandateModel;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.model.NachResponseModel;
import shfl.st.lap.nach.repo.NachRepo;

@Service
@AllArgsConstructor
public class NachService {
	
	private NachRepo nachRepo;
	
	private CustomerDepBankDetailsRepo customerDepBankDetailsRepo;
	
	private LosCustomerRepo losCustomerRepo;
	
	public ResponseEntity<String> registerNach(Nach nach) {
		nach.setStatus("To be Registered");
		Nach naachEntity=nachRepo.save(nach);
		if(Objects.nonNull(naachEntity)){
			return ResponseEntity.ok().body("Nach Registration Successfull");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nach Registration Not Successfull");
		}
	}
	
	public ResponseEntity<NachResponseModel> getNachDetails(MandateModel mandateModel) {
		Optional<Nach> data=nachRepo.findById(mandateModel.getMandateNum());
		Nach nach=new Nach();
		NachResponseModel nachResponseModel=new NachResponseModel();
		if (data.isPresent()) {
			nach=data.get();
			nachResponseModel=convertToResponse(nach);
		}
		return ResponseEntity.ok(nachResponseModel);
	}

	private NachResponseModel convertToResponse(Nach nach) {
		NachResponseModel nachResponseModel=new NachResponseModel();
		CustomerDepandantBankDetails customerDepandantBankDetails=customerDepBankDetailsRepo.findByBankAccountNumAndApplicationNum(nach.getBankAccountNum(), nach.getApplicationNum());
		Optional<LosCustomer> losCustomer=losCustomerRepo.findById(nach.getApplicationNum());
		nachResponseModel.setAccountType(customerDepandantBankDetails.getBankAccountType());
		nachResponseModel.setApplicationCustomer(losCustomer.get().getCustomerName());
		nachResponseModel.setApplicationNum(nach.getApplicationNum());
		nachResponseModel.setBankAccHolderName(customerDepandantBankDetails.getAccHoldrName());
		nachResponseModel.setBankAccountNum(customerDepandantBankDetails.getBankAccountNum());
		nachResponseModel.setBankName(customerDepandantBankDetails.getBankName());
		nachResponseModel.setBranch(losCustomer.get().getBranch());
		nachResponseModel.setBranchName(customerDepandantBankDetails.getBankBranchName());
		nachResponseModel.setCustomerEmailId(losCustomer.get().getEmailId());
		nachResponseModel.setCustomerMobileNum(losCustomer.get().getMobileNumber());
		nachResponseModel.setDebitType(nach.getDebitType());
		nachResponseModel.setEmiAmt(15000);
		nachResponseModel.setFbd(nach.getFbd());
		nachResponseModel.setFirstNachBillingDate(nach.getFirstNachBillingDate());
		nachResponseModel.setFrequency(nach.getFrequency());
		nachResponseModel.setHeaderKey("");
		nachResponseModel.setMandateAmt(nach.getMandateAmt());
		nachResponseModel.setMandateNum(nach.getMandateNum());
		nachResponseModel.setMandateStartDate(nach.getMandateStartDate());
		nachResponseModel.setMandateValidity(nach.getMandateValidity());
		nachResponseModel.setMaximumAmt(nach.getMaximumAmt());
		nachResponseModel.setMicr(customerDepandantBankDetails.getMicrCode());
		nachResponseModel.setNachAmt(nach.getNachAmt());
		nachResponseModel.setStatus(nach.getStatus());
		return nachResponseModel;
		
	}

}
