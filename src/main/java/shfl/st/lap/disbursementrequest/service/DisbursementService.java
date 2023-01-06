package shfl.st.lap.disbursementrequest.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.util.DateConversion;
import shfl.st.lap.disbursementrequest.model.CustomerDisbNumber;
import shfl.st.lap.disbursementrequest.model.DisbursementBillingDay;
import shfl.st.lap.disbursementrequest.model.DisbursementFavour;
import shfl.st.lap.disbursementrequest.model.DisbursementHistory;
import shfl.st.lap.disbursementrequest.model.DisbursementModel;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementBillingDayRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementFavourRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementHistoryRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;

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
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,
					disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
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
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,
					disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
	}

	/**
	 * getAllDisbursementData method is used to get all the disbursement creation
	 * 
	 * @return disbursementRequestList
	 */
	public ResponseEntity<List<DisbursementRequest>> getAllDisbursementData() {
		List<DisbursementRequest> disbursementRequestList = disbursementRequestRepo.findAll();
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
		if (disbursementModel.getScreenMode().equals("APPROVED")) {
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

}
