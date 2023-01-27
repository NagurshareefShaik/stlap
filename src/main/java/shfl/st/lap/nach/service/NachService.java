package shfl.st.lap.nach.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;
import shfl.st.lap.loscustomer.model.CustomerDepandantBankDetails;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.CustomerDepBankDetailsRepo;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;
import shfl.st.lap.nach.enums.StatusEnum;
import shfl.st.lap.nach.model.Nach;
import shfl.st.lap.nach.model.NachResponseModel;
import shfl.st.lap.nach.repo.NachRepo;
import shfl.st.lap.repaymentschedule.model.AmortResposnseModel;
import shfl.st.lap.repaymentschedule.service.RepaymentService;

@Service
@AllArgsConstructor
public class NachService {

	private NachRepo nachRepo;

	private CustomerDepBankDetailsRepo customerDepBankDetailsRepo;

	private LosCustomerRepo losCustomerRepo;

	private DisbursementRequestRepo disbursementRequestRepo;

	private RepaymentService repaymentService;

	/**
	 * registerNach method is used to save the nach details
	 * 
	 * @param nach
	 * @return ResponseEntity<NachResponseModel>
	 */
	public ResponseEntity<NachResponseModel> registerNach(Nach nach) {
		nach.setStatus(StatusEnum.REGISTERED.name());
		if (nach.getMandateNum().isEmpty() || Objects.isNull(nach.getMandateNum())) {
			nach.setMandateNum(String.valueOf(ThreadLocalRandom.current().nextInt()));
		}
		Nach naachEntity = nachRepo.save(nach);
		NachResponseModel nachModel = new NachResponseModel();
		if (Objects.nonNull(naachEntity)) {
			nachModel = convertToResponse(naachEntity);
			return ResponseEntity.ok().body(nachModel);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new NachResponseModel());
		}
	}

	/**
	 * getNachDetails method is used to get the nach details
	 * 
	 * @param map
	 * @return ResponseEntity<NachResponseModel>
	 */
	public ResponseEntity<NachResponseModel> getNachDetails(Map<String, String> map) {
		String appNum = map.get("applicationNum");
		Nach nachData = nachRepo.findByApplicationNum(appNum);
		NachResponseModel nachResponseModel = new NachResponseModel();
		if (Objects.nonNull(nachData)) {
			nachResponseModel = convertToResponse(nachData);
		} else {
			Nach nach = new Nach();
			nach.setApplicationNum(appNum);
			nachResponseModel = convertToResponse(nach);
		}
		return ResponseEntity.ok(nachResponseModel);
	}

	/**
	 * convertToResponse method is used to convert the Nach model to customized Nach
	 * Response model
	 * 
	 * @param nach
	 * @return NachResponseModel
	 */
	private NachResponseModel convertToResponse(Nach nach) {
		NachResponseModel nachResponseModel = new NachResponseModel();
		CustomerDepandantBankDetails customerDepandantBankDetails = customerDepBankDetailsRepo
				.findByApplicationNum(nach.getApplicationNum()).get(0);
		Optional<LosCustomer> losCustomer = losCustomerRepo.findById(nach.getApplicationNum());
		Map<String, String> appMap = new HashMap<>();
		appMap.put("applicationNum", nach.getApplicationNum());
		AmortResposnseModel repaymentData = repaymentService.calculateRepaymentSchedule(appMap);
		double emiAmount = repaymentData.getEmiAmount();
		nachResponseModel.setAccountType(customerDepandantBankDetails.getBankAccountType());
		nachResponseModel.setApplicationCustomer(losCustomer.get().getCustomerName());
		nachResponseModel.setApplicationNum(nach.getApplicationNum());
		nachResponseModel.setBankAccHolderName(customerDepandantBankDetails.getAccHoldrName());
		nachResponseModel.setBankAccountNum(customerDepandantBankDetails.getBankAccountNum());
		nachResponseModel.setBankName(customerDepandantBankDetails.getBankName());
		nachResponseModel.setBranch(losCustomer.get().getBranch());
		nachResponseModel.setBranchName(customerDepandantBankDetails.getBankBranchName());
		nachResponseModel.setIfscCode(customerDepandantBankDetails.getIfscCode());
		nachResponseModel.setCustomerEmailId(losCustomer.get().getEmailId());
		nachResponseModel.setCustomerMobileNum(losCustomer.get().getMobileNumber());
		nachResponseModel.setCustomerId(losCustomer.get().getCustomerId());
		nachResponseModel.setDebitType((nach.getDebitType() != null) ? nach.getDebitType() : "");
		nachResponseModel.setEmiAmt((int) emiAmount);
		nachResponseModel.setFbd(repaymentData.getFbd());
		nachResponseModel.setFirstNachBillingDate(repaymentData.getFbd());
		nachResponseModel.setFrequency(nach.getFrequency());
		nachResponseModel.setMandateAmt((int) emiAmount * 2);
		nachResponseModel.setMandateNum((nach.getMandateNum() != null) ? nach.getMandateNum() : "");
		nachResponseModel.setMandateStartDate(nach.getMandateStartDate());
		nachResponseModel.setLoanAmount(repaymentData.getSanctionAmount());
		// TODO repayment structure
		nachResponseModel.setMandateValidity(repaymentData.getMandateValidity());
		nachResponseModel.setMandateEndDate(repaymentData.getMandateValidity());
		nachResponseModel.setRepay("NACH");
		nachResponseModel.setRepayApplication("NACH");
		nachResponseModel.setMaximumAmt((int) emiAmount * 2);
		nachResponseModel.setMicr(customerDepandantBankDetails.getMicrCode());
		nachResponseModel.setNachAmt((int) emiAmount);
		nachResponseModel.setDraweePlace("chennai");
		nachResponseModel.setStatus((nach.getStatus() != null) ? nach.getStatus() : StatusEnum.NEW.name());
		nachResponseModel.setUmrnNumber(nach.getUmrnNumber());
		return nachResponseModel;

	}

	/**
	 * getNachVerification method is used to get the verified data from nach table.
	 * 
	 * @param filterParams
	 * @return ResponseEntity<List<NachResponseModel>>
	 */
	public ResponseEntity<List<NachResponseModel>> getNachDataByStatus(Map<String, String> branchMap) {
		List<Nach> nachList = nachRepo.findByBranchAndStatus(branchMap.get("branch"), branchMap.get("status"));
		List<NachResponseModel> nachRespList = nachList.stream().map(nach -> {
			return convertToResponse(nach);
		}).collect(Collectors.toList());
		return ResponseEntity.ok(nachRespList);
	}

	/**
	 * nachStatusChange method is used to change the status of the nach based upon
	 * operation.
	 * 
	 * @param map
	 * @return ResponseEntity<NachResponseModel>
	 */
	public ResponseEntity<NachResponseModel> nachStatusChange(Map<String, String> map) {
		NachResponseModel nachResponseModel = new NachResponseModel();
		Nach nach = nachRepo.findByApplicationNum(map.get("applicationNum"));
		if (Objects.nonNull(nach)) {
			nach.setStatus(map.get("status"));
			if (map.get("mode").equals(StatusEnum.VERIFIED.name())) {
				Random random = new Random();
				int umrnNumber = 10000000 + random.nextInt(90000000);
				nach.setUmrnNumber(umrnNumber);
			}
			Nach nachResponse = nachRepo.save(nach);
			nachResponseModel = convertToResponse(nachResponse);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nachResponseModel);
		}
		return ResponseEntity.ok(nachResponseModel);
	}

	/**
	 * getRequestedDisbData method is used to get the requested disbursement data
	 * from database.
	 * 
	 * @return ResponseEntity<List<NachResponseModel>>
	 */
	public ResponseEntity<NachResponseModel> getRequestedDisbData(Map<String, String> map) {
		DisbursementRequest requestedDisbData = disbursementRequestRepo.findByBranchAndApplicationNumAndRequestStatus(
				map.get("branch"), map.get("applicationNum"), "requested");
		NachResponseModel nachResponse = new NachResponseModel();
		if (Objects.nonNull(requestedDisbData)) {
			Nach nachData = nachRepo.findByBranchAndApplicationNum(requestedDisbData.getBranch(),
					requestedDisbData.getApplicationNum());
			if (Objects.nonNull(nachData)) {
				nachResponse = convertToResponse(nachData);
			} else {
				Nach nach = new Nach();
				nach.setApplicationNum(requestedDisbData.getApplicationNum());
				nachResponse = convertToResponse(nach);
			}
		}
		;
		return ResponseEntity.ok(nachResponse);
	}
	
	/**
	 * enachDetails method is used to get the emi data based upon loan details
	 * 
	 * @param losCustomer
	 * @return double
	 */
	public Map<String, Object> enachDetails(Map<String,String> enach) {
		Map<String,Object> returnMap = new HashMap<>();
		String applicationNum = getString(enach.get("applicationNum"));
		String applicatnName = getString(enach.get("applicantName"));
		AmortResposnseModel amortResponse=repaymentService.calculateRepaymentSchedule(enach);
		List<CustomerDepandantBankDetails> bankDetails = customerDepBankDetailsRepo.findByApplicationNum(applicationNum);
		List<LosCustomer> enachDetails = losCustomerRepo.findByapplicationNumAndCoApplicantName(applicationNum,applicatnName);
		returnMap.put("losData", enachDetails.get(0));
		returnMap.put("bankDetails", bankDetails.get(0));
		returnMap.put("nachAmount", amortResponse);
		return returnMap;
	}

	private String getString(Object object) {
		return Objects.isNull(object)?"":object.toString();
	}

	public List<Map<String, Object>> getApplicants(Map<String, Object> enach) {
		List<Map<String,Object>> applicanctList = new ArrayList<>();
		String applicationNum = getString(enach.get("applicationNum"));
		List<LosCustomer> returnData = losCustomerRepo.findByapplicationNum(applicationNum);
		Map<String,Object> applicantMap = new HashMap<>();
		applicantMap.put("text", returnData.get(0).getCoApplicantName());
		applicantMap.put("value", 1);
		applicanctList.add(applicantMap);
		applicantMap.put("text", returnData.get(0).getCustomerName());
		applicantMap.put("value", 2);
		applicanctList.add(applicantMap);
		return applicanctList;
	}

	/**
	 * getAllCreatedAndVerifiedNachData method is used to fetch created and verified
	 * data
	 * 
	 * @return nachResponseModelList
	 */
	public ResponseEntity<List<NachResponseModel>> getAllCreatedAndVerifiedNachData() {
		Set<String> statusList = new HashSet<>();
		statusList.add(StatusEnum.REGISTERED.name());
		statusList.add(StatusEnum.VERIFIED.name());
		List<Nach> nachDataList = nachRepo.findByStatusIn(statusList);
		List<NachResponseModel> nachResponseModelList = nachDataList.stream().map(nach -> {
			return convertToResponse(nach);
		}).collect(Collectors.toList());
		return ResponseEntity.ok(nachResponseModelList);
	}

	/**
	 * getAppNumAndBranchByVerifiedStatus method is used to fetch application
	 * numbers and branch for verified status
	 * 
	 * @return applicationNumList
	 */
	public ResponseEntity<List<Map<String, String>>> getAppNumAndBranchByVerifiedStatus() {
		List<Map<String, String>> appNumAndbranchList = new ArrayList<>();
		List<Nach> nachStatusList = nachRepo.findByStatus("VERIFIED");
		nachStatusList.stream().forEach(data -> {
			Map<String, String> branchmap = new HashMap<>();
			branchmap.put("branch", data.getBranch());
			branchmap.put("applicationNum", data.getApplicationNum());
			appNumAndbranchList.add(branchmap);
		});
		return ResponseEntity.ok(appNumAndbranchList);
	}

}
