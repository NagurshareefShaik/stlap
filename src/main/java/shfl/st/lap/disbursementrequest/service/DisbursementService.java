package shfl.st.lap.disbursementrequest.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import shfl.st.lap.disbursementrequest.model.CustomerDisbNumber;
import shfl.st.lap.disbursementrequest.model.DisbAppModel;
import shfl.st.lap.disbursementrequest.model.DisbPagenationModel;
import shfl.st.lap.disbursementrequest.model.DisbursementBillingDay;
import shfl.st.lap.disbursementrequest.model.DisbursementFavour;
import shfl.st.lap.disbursementrequest.model.DisbursementHistory;
import shfl.st.lap.disbursementrequest.model.DisbursementModel;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementBillingDayRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementFavourRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementHistoryRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;
import shfl.st.lap.feeaccrual.service.FeeAccrualWaiverService;
import shfl.st.lap.ledger.model.LedgerMain;
import shfl.st.lap.ledger.model.LedgerStage;
import shfl.st.lap.ledger.repo.LedgerMainRepo;
import shfl.st.lap.ledger.repo.LedgerStageRepo;
import shfl.st.lap.ledger.util.LedgerData;
import shfl.st.lap.util.DateConversion;

@Service
public class DisbursementService {

	private static final String MODULEID = "MD001";
	private static final String CREATEMODULEID = "MD001C";
	private static final String MODIFYMODULEID = "MD001M";
	private static final String CANCELMODULEID = "MD001D";
	private static final String APPROVEDMODULEID = "MD001A";

	@Autowired
	DisbursementRequestRepo disbursementRequestRepo;

	@Autowired
	DisbursementHistoryRepo disbursementHistoryRepo;

	@Autowired
	DisbursementFavourRepo disbursementFavourRepo;

	@Autowired
	DisbursementBillingDayRepo disbursementBillingDayRepo;

	@Autowired
	DateConversion dateConversion;

	@Autowired
	LedgerStageRepo ledgerStageRepo;

	@Autowired
	LedgerMainRepo ledgerMainRepo;
	
	@Autowired
	FeeAccrualWaiverService accrualWaiverService; 
	
	@Autowired
	LedgerData ledgerData;

	/**
	 * insertDisbursementData method is used to insert value to corresponding table
	 * 
	 * @param disbursementModel
	 * @return disbursementModelData
	 */
	@Transactional
	public ResponseEntity<DisbursementModel> insertDisbursementData(DisbursementModel disbursementModel) {
		if (Objects.nonNull(disbursementModel)) {
			DisbursementRequest disbursementRequestData = setDisbursementRequestData(disbursementModel);
			setDisbursementHistoryData(disbursementRequestData, disbursementModel);
			List<DisbursementFavour> disbursementFavourDataList = setDisbursementFavourData(disbursementModel,
					disbursementRequestData.getDisbHeaderKey());
			setLedgerData(disbursementRequestData, disbursementModel.getScreenMode());
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,
					disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
	}

	private void setLedgerData(DisbursementRequest disbursementRequestData, String mode) {
		List<LedgerStage> ledgerDataList = ledgerData.getLedgerData();
		if (mode.equals("CREATE")) {
			insertLedgerData(ledgerDataList, disbursementRequestData);
			insertLedgerDeductions(disbursementRequestData);
		} else if (mode.equals("MODIFY")) {
			List<LedgerStage> ledgerStageKeyList = ledgerStageRepo
					.findByHeaderKey(disbursementRequestData.getDisbHeaderKey());
			ledgerStageRepo.deleteAll(ledgerStageKeyList);
			insertLedgerData(ledgerDataList, disbursementRequestData);
			insertLedgerDeductions(disbursementRequestData);
		} else if (mode.equals("APPROVE")) {
			List<LedgerStage> ledgerStageKeyList = ledgerStageRepo
					.findByHeaderKey(disbursementRequestData.getDisbHeaderKey());
			List<LedgerMain> ledgerMainList = convertStageToMain(ledgerStageKeyList);
			ledgerStageRepo.deleteAll(ledgerStageKeyList);
			ledgerMainRepo.saveAll(ledgerMainList);
		}

	}

	private void insertLedgerDeductions(DisbursementRequest disbursementRequestData) {
		Map<String,Object> dataMap=new HashMap<>();
		Map<String,String> feeDescBankDataMap=ledgerData.getFeeDescriptionBankData();
		dataMap.put("applicationNum", disbursementRequestData.getApplicationNum());
		dataMap.put("type", "accrual");
		ResponseEntity<Map<String, Object>> accrualWaiverDataMap=accrualWaiverService.getFeeData(dataMap);
		List<LedgerStage> ledgerStageList=new ArrayList<>();
		List<Map<String,Object>> data=(List<Map<String, Object>>) accrualWaiverDataMap.getBody().get("gridData");
		data.stream().forEach(feeDed->{
			LedgerStage ledgerStage=new LedgerStage();
			int amount =((int) feeDed.get("receiveable"))-((int)feeDed.get("received"))-((int)feeDed.get("earlyWaiver"));
			ledgerStage.setAccountingType("BRANCH");
			ledgerStage.setBranchCode(disbursementRequestData.getBranch());
			ledgerStage.setCharset("STDSJV");
			ledgerStage.setEffectiveDate(new Date());
			ledgerStage.setHeaderKey(disbursementRequestData.getDisbHeaderKey());
			ledgerStage.setModuleCode(1);
			ledgerStage.setModuleId(MODULEID);
			ledgerStage.setNarration("");
			ledgerStage.setReferenceNum(disbursementRequestData.getApplicationNum());
			ledgerStage.setTxnAccount(feeDescBankDataMap.get(feeDed.get("id")));
			ledgerStage.setTxnAmt(amount);
			ledgerStage.setVoucherDate(new Date());
			ledgerStage.setReferenceType("FILE");
			ledgerStage.setVoucherNum(1);
			ledgerStage.setTxnCode(0);
			ledgerStage.setNarration(feeDed.get("id").toString());
			ledgerStageList.add(ledgerStage);
		});
		ledgerStageRepo.saveAll(ledgerStageList);
	}

	private List<LedgerMain> convertStageToMain(List<LedgerStage> ledgerStageKeyList) {
		List<LedgerMain> ledgerMainList = new ArrayList<>();
		ledgerStageKeyList.stream().forEach(ledger -> {
			LedgerMain ledgerMain = new LedgerMain();
			ledgerMain.setAccountingType(ledger.getAccountingType());
			ledgerMain.setBranchCode(ledger.getBranchCode());
			ledgerMain.setCharset(ledger.getCharset());
			ledgerMain.setEffectiveDate(ledger.getEffectiveDate());
			ledgerMain.setHeaderKey(ledger.getHeaderKey());
			ledgerMain.setModuleCode(ledger.getModuleCode());
			ledgerMain.setModuleId(ledger.getModuleId());
			ledgerMain.setNarration(ledger.getNarration());
			ledgerMain.setReferenceNum(ledger.getReferenceNum());
			ledgerMain.setReferenceType(ledger.getReferenceType());
			ledgerMain.setTxnAccount(ledger.getTxnAccount());
			ledgerMain.setTxnAmt(ledger.getTxnAmt());
			ledgerMain.setVoucherDate(ledger.getVoucherDate());
			ledgerMain.setVoucherNum(ledger.getVoucherNum());
			ledgerMainList.add(ledgerMain);
		});
		return ledgerMainList;
	}

	private void insertLedgerData(List<LedgerStage> ledgerDataList, DisbursementRequest disbursementRequestData) {
		ledgerDataList.stream().forEach(ledger -> {

			if (ledger.getAccountingType().equals("BRANCH")) {
				ledger.setBranchCode(disbursementRequestData.getBranch());
				ledger.setCharset("STDSJV");
				ledger.setReferenceNum(disbursementRequestData.getApplicationNum());
				if (ledger.getTxnCode() == 0) {
					ledger.setTxnAmt(disbursementRequestData.getTotalDisbAmt());
				} else {
					ledger.setTxnAmt(disbursementRequestData.getDisbAmt());
				}
			} else {
				ledger.setBranchCode(null);
				ledger.setCharset("STDSPV");
				ledger.setReferenceNum(null);
				ledger.setTxnAmt(disbursementRequestData.getDisbAmt());
			}
			ledger.setHeaderKey(disbursementRequestData.getDisbHeaderKey());
			ledger.setEffectiveDate(new Date());
			ledger.setModuleId(MODULEID);
			ledger.setModuleCode(1);
			ledger.setNarration("narration");
			ledger.setVoucherDate(new Date());
			ledger.setVoucherNum(1);
		});
		ledgerStageRepo.saveAll(ledgerDataList);
	}

	/**
	 * getDisbursementData method is used to get single disbursed value from the
	 * table
	 * 
	 * @param customerDisbNumber
	 * @return disbModel
	 */
	public ResponseEntity<DisbursementModel> getDisbursementData(CustomerDisbNumber customerDisbNumber) {
		Optional<DisbursementRequest> disbRequest = disbursementRequestRepo
				.findById(customerDisbNumber.getDisbHeaderKey());
		if (disbRequest.isPresent()) {
			List<DisbursementFavour> disbursementFavourList = disbursementFavourRepo
					.findByDisbHeaderKey(disbRequest.get().getDisbHeaderKey());
			DisbursementModel disbModel = getDisbursementModelData(disbRequest.get(), disbursementFavourList);
			if ((customerDisbNumber.getScreenMode().equals("MODIFY")
					|| customerDisbNumber.getScreenMode().equals("CANCEL")) && !(disbModel.isEditLock())) {
				disbRequest.get().setEditLock(true);
				disbursementRequestRepo.save(disbRequest.get());
			}
			if (Objects.nonNull(disbModel)) {
				return ResponseEntity.ok().body(disbModel);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
	}

	/**
	 * updateDisbursementData method is used to update the disbursement value to
	 * table
	 * 
	 * @param disbursementModel
	 * @return disbursementModelData
	 */
	public ResponseEntity<DisbursementModel> updateDisbursementData(DisbursementModel disbursementModel) {
		if (Objects.nonNull(disbursementModel)) {
			DisbursementRequest disbursementRequestData = setDisbursementRequestData(disbursementModel);
			setDisbursementHistoryData(disbursementRequestData, disbursementModel);
			List<DisbursementFavour> disbursementFavourDataList = setDisbursementFavourData(disbursementModel,
					disbursementRequestData.getDisbHeaderKey());
			setLedgerData(disbursementRequestData, disbursementModel.getScreenMode());
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,
					disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
	}

	/**
	 * getAllDisbursementData method is used to get all the disbursement creation
	 * @param disbPagenationModel 
	 * 
	 * @return disbursementRequestList
	 */
	public ResponseEntity<Page<DisbursementRequest>> getAllDisbursementData(DisbPagenationModel disbPagenationModel) {
		Pageable pageable=PageRequest.of(disbPagenationModel.getOffset(), disbPagenationModel.getPageSize(),Sort.by("applicationNum").descending());
		Page<DisbursementRequest> disbursementRequestList = disbursementRequestRepo.findAll(new Specification<DisbursementRequest>() {
			
			@Override
			public Predicate toPredicate(Root<DisbursementRequest> root, CriteriaQuery<?> critriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate predicate=criteriaBuilder.conjunction();
				if(!disbPagenationModel.getBranch().isEmpty()) {
					predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("branch"), disbPagenationModel.getBranch()));
				}
				if(!disbPagenationModel.getApplicationNum().isEmpty()) {
					predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("applicationNum"), disbPagenationModel.getApplicationNum()));
				}
				if(!disbPagenationModel.getApplicantName().isEmpty()) {
					predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("applicantName"), disbPagenationModel.getApplicantName()));
				}
				if(!disbPagenationModel.getDisbursementStatus().isEmpty()) {
					predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("disbursementStatus"), disbPagenationModel.getDisbursementStatus()));
				}
				return predicate;
			}
		},pageable);
		return ResponseEntity.ok().body(disbursementRequestList);
	}

	/**
	 * setDisbursementRequestData method is used to form the DisbursementRequest
	 * entity and insert the table
	 * 
	 * @param disbursementModel
	 * @return disbursementRequest
	 */
	private DisbursementRequest setDisbursementRequestData(DisbursementModel disbursementModel) {
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		if (disbursementModel.getScreenMode().equals("CREATE")) {
			SecureRandom secureRandom;
			try {
				secureRandom = SecureRandom.getInstance("SHA1PRNG");
				int disbRandomValue = secureRandom.nextInt();
				if (disbRandomValue < 0) {
					disbursementRequest.setDisbHeaderKey(disbRandomValue * -1);
				} else {
					disbursementRequest.setDisbHeaderKey(disbRandomValue);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (disbursementModel.getScreenMode().equals("CREATE")) {
			SecureRandom secureRandom;
			try {
				secureRandom = SecureRandom.getInstance("SHA1PRNG");
				int transRandomValue = secureRandom.nextInt();
				if (transRandomValue < 0) {
					disbursementRequest.setTransactionKey(transRandomValue * -1);
				} else {
					disbursementRequest.setTransactionKey(transRandomValue);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!disbursementModel.getScreenMode().equals("CREATE")) {
			disbursementRequest.setDisbHeaderKey(disbursementModel.getDisbHeaderKey());
			disbursementRequest.setTransactionKey(disbursementModel.getTransactionKey());
		}
		disbursementRequest.setApplicationNum(disbursementModel.getApplicationNum());
		disbursementRequest.setBranch(disbursementModel.getBranch());
		disbursementRequest.setApplicantName(disbursementModel.getApplicantName());
		disbursementRequest.setEarlierDisbAmt(disbursementModel.getEarlierDisbAmt());
		disbursementRequest.setDisbAmt(disbursementModel.getDisbAmt());
		disbursementRequest.setDisbNum(disbursementModel.getDisbNum());
		disbursementRequest.setRateOfInterest(disbursementModel.getRateOfInterest());
		disbursementRequest.setTotalDisbAmt(disbursementModel.getTotalDisbAmt());
		disbursementRequest.setDateOfDisb(disbursementModel.getDateOfDisb());
		disbursementRequest.setBillDay(disbursementModel.getBillDay());
		disbursementRequest.setBillingDate(disbursementModel.getBillingDate());
		disbursementRequest.setEmiCommDate(disbursementModel.getEmiCommDate());
		disbursementRequest.setFirstEmiDueDate(disbursementModel.getFirstEmiDueDate());
		disbursementRequest.setEffectiveDate(disbursementModel.getEffectiveDate());
		disbursementRequest.setRequestStatus(disbursementModel.getRequestStatus());
		disbursementRequest.setPaymentMode(disbursementModel.getPaymentMode());
		disbursementRequest.setRemarks(disbursementModel.getRemarks());
		disbursementRequest.setEditLock(false);
		disbursementRequest.setModuleId(MODULEID);
		disbursementRequest.setDisbEmiAmt(disbursementModel.getDisbEmiAmt());
		disbursementRequest.setTotalDeductionAmt(disbursementModel.getTotalDeductionAmt());
		disbursementRequest.setApprovalRemarks(disbursementModel.getApprovalRemarks());
		return disbursementRequestRepo.save(disbursementRequest);
	}

	/**
	 * setDisbursementHistoryData method is used to form the DisbursementHistory
	 * entity and insert the table
	 * 
	 * @param disbursementRequestData
	 * @param disbursementModel
	 * @return disbursementHistory
	 */
	private DisbursementHistory setDisbursementHistoryData(DisbursementRequest disbursementRequestData,
			DisbursementModel disbursementModel) {
		DisbursementHistory disbursementHistory = new DisbursementHistory();
		disbursementHistory.setDisbHistoryKey(ThreadLocalRandom.current().nextInt());
		disbursementHistory.setDisbHeaderKey(disbursementRequestData.getDisbHeaderKey());
		disbursementHistory.setTransactionKey(disbursementRequestData.getTransactionKey());
		disbursementHistory.setApplicationNum(disbursementRequestData.getApplicationNum());
		disbursementHistory.setBranch(disbursementRequestData.getBranch());
		disbursementHistory.setApplicantName(disbursementRequestData.getApplicantName());
		disbursementHistory.setEarlierDisbAmt(disbursementRequestData.getEarlierDisbAmt());
		disbursementHistory.setDisbAmt(disbursementRequestData.getDisbAmt());
		disbursementHistory.setDisbNum(disbursementRequestData.getDisbNum());
		disbursementHistory.setRateOfInterest(disbursementRequestData.getRateOfInterest());
		disbursementHistory.setTotalDisbAmt(disbursementRequestData.getTotalDisbAmt());
		disbursementHistory.setDateOfDisb(disbursementRequestData.getDateOfDisb());
		disbursementHistory.setBillDay(disbursementRequestData.getBillDay());
		disbursementHistory.setBillingDate(disbursementRequestData.getBillingDate());
		disbursementHistory.setEmiCommDate(disbursementRequestData.getEmiCommDate());
		disbursementHistory.setFirstEmiDueDate(disbursementRequestData.getFirstEmiDueDate());
		disbursementHistory.setRequestStatus(disbursementRequestData.getRequestStatus());
		disbursementHistory.setPaymentMode(disbursementRequestData.getPaymentMode());
		disbursementHistory.setRemarks(disbursementRequestData.getRemarks());
		if (disbursementModel.getScreenMode().equals("CREATE")) {
			disbursementHistory.setModuleId(CREATEMODULEID);
		}
		if (disbursementModel.getScreenMode().equals("MODIFY")) {
			disbursementHistory.setModuleId(MODIFYMODULEID);
		}
		if (disbursementModel.getScreenMode().equals("CANCEL")) {
			disbursementHistory.setModuleId(CANCELMODULEID);
		}
		if (disbursementModel.getScreenMode().equals("APPROVE")) {
			disbursementHistory.setModuleId(APPROVEDMODULEID);
		}
		disbursementHistory.setDisbEmiAmt(disbursementRequestData.getDisbEmiAmt());
		disbursementHistory.setTotalDeductionAmt(disbursementRequestData.getTotalDeductionAmt());
		disbursementHistory.setApprovalRemarks(disbursementRequestData.getApprovalRemarks());
		return disbursementHistoryRepo.save(disbursementHistory);
	}

	/**
	 * setDisbursementFavourData method is used to form the DisbursementFavour
	 * entity and insert the table
	 * 
	 * @param disbursementModel
	 * @param disbHeaderKey
	 * @return disbursementFavoursList
	 */
	private List<DisbursementFavour> setDisbursementFavourData(DisbursementModel disbursementModel, int disbHeaderKey) {
		List<DisbursementFavour> disbursementFavoursList = new ArrayList<>();
		disbursementModel.getDisbursementFavours().stream().forEach(favour -> {
			DisbursementFavour disbursementFavour = new DisbursementFavour();
			if (disbursementModel.getScreenMode().equals("CREATE")) {
				disbursementFavour.setDisbAccountKey(ThreadLocalRandom.current().nextInt());
			}
			if (!disbursementModel.getScreenMode().equals("CREATE")) {
				disbursementFavour.setDisbAccountKey(favour.getDisbAccountKey());
			}
			disbursementFavour.setBankAccountNum(favour.getBankAccountNum());
			disbursementFavour.setDisbHeaderKey(disbHeaderKey);
			disbursementFavour.setApplicationNum(favour.getApplicationNum());
			disbursementFavour.setDisbNum(favour.getDisbNum());
			disbursementFavour.setDisbAmt(favour.getDisbAmt());
			disbursementFavour.setUtrNum(favour.getUtrNum());
			disbursementFavoursList.add(disbursementFavour);
		});
		return disbursementFavourRepo.saveAll(disbursementFavoursList);
	}

	/**
	 * getDisbursementModelData method is used to form the DisbursementModel entity
	 * 
	 * @param disbursementRequestData
	 * @param disbursementFavourDataList
	 * @return disbursementModel
	 */
	private DisbursementModel getDisbursementModelData(DisbursementRequest disbursementRequestData,
			List<DisbursementFavour> disbursementFavourDataList) {
		DisbursementModel disbursementModel = new DisbursementModel();
		disbursementModel.setDisbHeaderKey(disbursementRequestData.getDisbHeaderKey());
		disbursementModel.setTransactionKey(disbursementRequestData.getTransactionKey());
		disbursementModel.setApplicationNum(disbursementRequestData.getApplicationNum());
		disbursementModel.setBranch(disbursementRequestData.getBranch());
		disbursementModel.setApplicantName(disbursementRequestData.getApplicantName());
		disbursementModel.setEarlierDisbAmt(disbursementRequestData.getEarlierDisbAmt());
		disbursementModel.setDisbAmt(disbursementRequestData.getDisbAmt());
		disbursementModel.setDisbNum(disbursementRequestData.getDisbNum());
		disbursementModel.setRateOfInterest(disbursementRequestData.getRateOfInterest());
		disbursementModel.setTotalDisbAmt(disbursementRequestData.getTotalDisbAmt());
		disbursementModel.setDateOfDisb(disbursementRequestData.getDateOfDisb());
		disbursementModel.setBillDay(disbursementRequestData.getBillDay());
		disbursementModel.setBillingDate(disbursementRequestData.getBillingDate());
		disbursementModel.setEmiCommDate(disbursementRequestData.getEmiCommDate());
		disbursementModel.setFirstEmiDueDate(disbursementRequestData.getFirstEmiDueDate());
		disbursementModel.setEffectiveDate(disbursementRequestData.getEffectiveDate());
		disbursementModel.setRequestStatus(disbursementRequestData.getRequestStatus());
		disbursementModel.setPaymentMode(disbursementRequestData.getPaymentMode());
		disbursementModel.setRemarks(disbursementRequestData.getRemarks());
		disbursementModel.setEditLock(disbursementRequestData.isEditLock());
		List<DisbursementFavour> disbursementFavoursList = new ArrayList<>();
		disbursementFavourDataList.stream().forEach(data -> {
			DisbursementFavour disbursementFavour = new DisbursementFavour();
			disbursementFavour.setBankAccountNum(data.getBankAccountNum());
			disbursementFavour.setDisbHeaderKey(data.getDisbHeaderKey());
			disbursementFavour.setApplicationNum(data.getApplicationNum());
			disbursementFavour.setDisbNum(data.getDisbNum());
			disbursementFavour.setDisbAmt(data.getDisbAmt());
			disbursementFavour.setUtrNum(data.getUtrNum());
			disbursementFavour.setDisbAccountKey(data.getDisbAccountKey());
			disbursementFavoursList.add(disbursementFavour);
		});
		disbursementModel.setDisbursementFavours(disbursementFavoursList);
		disbursementModel.setDisbEmiAmt(disbursementRequestData.getDisbEmiAmt());
		disbursementModel.setTotalDeductionAmt(disbursementRequestData.getTotalDeductionAmt());
		disbursementModel.setApprovalRemarks(disbursementRequestData.getApprovalRemarks());
		return disbursementModel;
	}

	/**
	 * registerBillingDay method is used to register the day for billing
	 * 
	 * @param disbursementBillingDay
	 * @return msg
	 */
	public String registerBillingDay(DisbursementBillingDay disbursementBillingDay) {
		String msg = "";
		DisbursementBillingDay billingData = disbursementBillingDayRepo.save(disbursementBillingDay);
		if (Objects.nonNull(billingData)) {
			msg = "Billing Day Registration successfull";
		} else {
			msg = "Billing Day Registration Not Successfull";
		}
		return msg;
	}

	/**
	 * getAllDisbursementBillingDayData method is used to get all the billing day
	 * 
	 * @return disbursementBillingDayDataList
	 */
	public ResponseEntity<List<DisbursementBillingDay>> getAllDisbursementBillingDayData() {
		List<DisbursementBillingDay> disbursementBillingDayDataList = disbursementBillingDayRepo.findAll();
		return ResponseEntity.ok().body(disbursementBillingDayDataList);
	}

	/**
	 * searchAllDisbBranchData method is used to search branch in disbursement
	 * details
	 * 
	 * @param brnach
	 * @return disbursementRequestList
	 */
	public ResponseEntity<List<DisbursementRequest>> searchAllDisbBranchData(String branch) {
		List<DisbursementRequest> disbursementRequestList = disbursementRequestRepo.findByBranch(branch);
		return ResponseEntity.ok().body(disbursementRequestList);
	}

	/**
	 * editLockUpdate method is used to user close the window without click submit
	 * button edit lock will be false
	 * 
	 * @param customerDisbNumber
	 * @return DisbursementRequest
	 */
	public ResponseEntity<DisbursementRequest> editLockUpdate(CustomerDisbNumber customerDisbNumber) {
		Optional<DisbursementRequest> disbRequestData = disbursementRequestRepo
				.findById(customerDisbNumber.getDisbHeaderKey());
		if (disbRequestData.isPresent()) {
			disbRequestData.get().setEditLock(false);
			disbursementRequestRepo.save(disbRequestData.get());
		}
		return ResponseEntity.ok().body(disbRequestData.get());
	}

	/**
	 * 
	 * @param disbAppModel
	 * @return firstDisbData
	 */
	public ResponseEntity<List<DisbursementRequest>> getFirstDisbByAppNum(DisbAppModel disbAppModel) {
		List<DisbursementRequest> firstDisbData = disbursementRequestRepo
				.findByApplicationNum(disbAppModel.getApplicationNum());
		if (Objects.nonNull(firstDisbData)) {
			return ResponseEntity.ok().body(firstDisbData);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
	}

}
