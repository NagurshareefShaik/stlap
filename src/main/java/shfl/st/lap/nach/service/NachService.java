package shfl.st.lap.nach.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
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
import shfl.st.lap.nach.model.NachFilterParams;
import shfl.st.lap.nach.model.NachResponseModel;
import shfl.st.lap.nach.model.PreVerificationModel;
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
	 * @return ResponseEntity<Nach>
	 */
	public ResponseEntity<Nach> registerNach(Nach nach) {
		nach.setStatus(StatusEnum.REGISTERED.name());
		Nach naachEntity = nachRepo.save(nach);
		if (Objects.nonNull(naachEntity)) {
			return ResponseEntity.ok().body(naachEntity);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Nach());
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
		Map<String,String> appMap=new HashMap<>();
		appMap.put("applicationNum", nach.getApplicationNum());
		AmortResposnseModel repaymentData=repaymentService.calculateRepaymentSchedule(appMap);
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
		nachResponseModel.setFbd(nach.getFbd());
		nachResponseModel.setFirstNachBillingDate(nach.getFirstNachBillingDate());
		nachResponseModel.setFrequency(nach.getFrequency());
		nachResponseModel.setMandateAmt((int) emiAmount * 2);
		nachResponseModel.setMandateNum((nach.getMandateNum() != null) ? nach.getMandateNum() : "");
		nachResponseModel.setMandateStartDate(nach.getMandateStartDate());
		nachResponseModel.setLoanAmount(repaymentData.getSanctionAmount());
		// TODO repayment structure
		nachResponseModel.setMandateValidity(repaymentData.getMandateValidity());
		nachResponseModel.setMandateEndDate(nach.getMandateEndDate());
		nachResponseModel.setRepay("NACH");
		nachResponseModel.setRepayApplication("NACH");
		nachResponseModel.setMaximumAmt((int) emiAmount * 2);
		nachResponseModel.setMicr(customerDepandantBankDetails.getMicrCode());
		nachResponseModel.setNachAmt((int) emiAmount);
		nachResponseModel.setDraweePlace("chennai");
		nachResponseModel.setStatus((nach.getStatus() != null) ? nach.getStatus() : "");
		nachResponseModel.setUmrnNumber(nach.getUmrnNumber());
		return nachResponseModel;

	}

	/**
	 * getNachVerification method is used to get the pre verification done data from
	 * nach table.
	 * 
	 * @param filterParams
	 * @return ResponseEntity<List<NachResponseModel>>
	 */
	public ResponseEntity<List<NachResponseModel>> getNachVerification(NachFilterParams filterParams) {
		List<Nach> nachList = nachRepo.findAll(new Specification<Nach>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Nach> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.like(root.get("status"), StatusEnum.PRE_VERIFICATION_DONE.name()));
				if (!filterParams.getBranch().isEmpty()) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.like(root.get("branch"), filterParams.getBranch()));
				}
				if (!filterParams.getApplicationNumber().isEmpty()) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.like(root.get("applicationNum"), filterParams.getApplicationNumber()));
				}
				return predicate;
			}
		});
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
			if(map.get("mode").equals("approve")) {
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

	public ResponseEntity<NachResponseModel> preVerification(PreVerificationModel preVerificationModel) {
		Map<String, String> applicationMap = new HashMap<>();
		applicationMap.put("applicationNum", preVerificationModel.getApplicationNum());
		NachResponseModel nachResponseModel = getNachDetails(applicationMap).getBody();
		return ResponseEntity.ok(nachResponseModel);
	}

	/**
	 * getRequestedDisbData method is used to get the requested disbursement data
	 * from database.
	 * 
	 * @return ResponseEntity<List<NachResponseModel>>
	 */
	public ResponseEntity<NachResponseModel> getRequestedDisbData(Map<String, String> map) {
		DisbursementRequest requestedDisbData = disbursementRequestRepo.findByBranchAndApplicationNumAndRequestStatus(map.get("branch"),map.get("applicationNum"),"requested");
		NachResponseModel nachResponse = new NachResponseModel();
		if(Objects.nonNull(requestedDisbData)) {
			Nach nachData = nachRepo.findByBranchAndApplicationNum(requestedDisbData.getBranch(), requestedDisbData.getApplicationNum());
			if (Objects.nonNull(nachData)) {
				nachResponse = convertToResponse(nachData);
			} else {
				Nach nach = new Nach();
				nach.setApplicationNum(requestedDisbData.getApplicationNum());
				nachResponse = convertToResponse(nach);
			}
		};
		return ResponseEntity.ok(nachResponse);
	}

	/**
	 * amortCalc method is used to get the emi data based upon loan details
	 * 
	 * @param losCustomer
	 * @return double
	 */
	public double amortCalc(LosCustomer losCustomer) {
		double principal = losCustomer.getSanctionAmt();
		int time = losCustomer.getTenure();
		float roi = losCustomer.getRateOfInterest();
		roi = roi / (12 * 100);
		double emi = Math.round((principal * roi * Math.pow(1 + roi, time)) / (Math.pow(1 + roi, time) - 1));
		float firstMonthInterest = (float) (roi * principal);
		return emi;
	}

}
