package shfl.st.lap.disbursementrequest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.disbursementrequest.model.CustomerDisbNumber;
import shfl.st.lap.disbursementrequest.model.DisbursementFavour;
import shfl.st.lap.disbursementrequest.model.DisbursementHistory;
import shfl.st.lap.disbursementrequest.model.DisbursementModel;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementFavourRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementHistoryRepo;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;

@Service
public class DisbursementService {

	
	@Autowired
	DisbursementRequestRepo disbursementRequestRepo;
	
	@Autowired
	DisbursementHistoryRepo disbursementHistoryRepo;
	
	@Autowired
	DisbursementFavourRepo disbursementFavourRepo;
	
	/**
	 * 
	 * @param disbursementModel
	 * @return ResponseEntity
	 */
	public ResponseEntity<DisbursementModel> insertDisbursementData(DisbursementModel disbursementModel) {
		if (Objects.nonNull(disbursementModel)) {
			DisbursementRequest disbursementRequestData = setDisbursementRequestData(disbursementModel);
			setDisbursementHistoryData(disbursementRequestData);
			List<DisbursementFavour> disbursementFavourDataList = setDisbursementFavourData(disbursementModel);
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
	}
	
	/**
	 * 
	 * @param customerDisbNumber
	 * @return ResponseEntity
	 */
	public ResponseEntity<DisbursementModel> getDisbursementData(CustomerDisbNumber customerDisbNumber) {
		Optional<DisbursementRequest> disbRequest = disbursementRequestRepo.findById(customerDisbNumber.getDisbRequestId());
		if (disbRequest.isPresent()) {
			List<DisbursementFavour> disbursementFavourList = disbursementFavourRepo.findByApplicationNumber(disbRequest.get().getApplicationNumber());
			DisbursementModel disbModel = getDisbursementModelData(disbRequest.get(), disbursementFavourList);
			if (Objects.nonNull(disbModel)) {
				return ResponseEntity.ok().body(disbModel);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
	}
	
	/**
	 * 
	 * @param disbursementModel
	 * @return ResponseEntity
	 */
	public ResponseEntity<DisbursementModel> updateDisbursementData(DisbursementModel disbursementModel) {
		if (Objects.nonNull(disbursementModel)) {
			DisbursementRequest disbursementRequestData = setDisbursementRequestData(disbursementModel);
			setDisbursementHistoryData(disbursementRequestData);
			List<DisbursementFavour> disbursementFavourDataList = setDisbursementFavourData(disbursementModel);
			DisbursementModel disbursementModelData = getDisbursementModelData(disbursementRequestData,disbursementFavourDataList);
			return ResponseEntity.ok().body(disbursementModelData);	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DisbursementModel());
		}
	}
	
	/**
	 * 
	 * @return disbursementRequestList
	 */
	public ResponseEntity<List<DisbursementRequest>> getAllDisbursementData() {
		List<DisbursementRequest> disbursementRequestList = disbursementRequestRepo.findAll();
		return ResponseEntity.ok().body(disbursementRequestList);
	}

	/**
	 * 
	 * @param disbursementModel
	 * @return disbursementRequest
	 */
	private DisbursementRequest setDisbursementRequestData(DisbursementModel disbursementModel) {
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		if (disbursementModel.getScreenMode().equals("UPDATE")) {
			disbursementRequest.setDisbRequestId(disbursementModel.getDisbRequestId());
		}
		disbursementRequest.setApplicationNumber(disbursementModel.getApplicationNumber());
		disbursementRequest.setEarlierDisbAmt(disbursementModel.getEarlierDisbAmt());
		disbursementRequest.setDisbAmt(disbursementModel.getDisbAmt());
		disbursementRequest.setDisbNo(disbursementModel.getDisbNo());
		disbursementRequest.setTotalDisbAmt(disbursementModel.getTotalDisbAmt());
		disbursementRequest.setDateOfDisb(disbursementModel.getDateOfDisb());
		disbursementRequest.setBillingDay(disbursementModel.getBillingDay());
		disbursementRequest.setBillingDate(disbursementModel.getBillingDate());
		disbursementRequest.setEmiCommDate(disbursementModel.getEmiCommDate());
		disbursementRequest.setFirstEmiDueDate(disbursementModel.getFirstEmiDueDate());
		disbursementRequest.setEffectiveDate(disbursementModel.getEffectiveDate());
		disbursementRequest.setRequestStatus(disbursementModel.getRequestStatus());
		disbursementRequest.setPaymentMode(disbursementModel.getPaymentMode());
		disbursementRequest.setShflBank(disbursementModel.getShflBank());
		disbursementRequest.setRemarks(disbursementModel.getRemarks());
		disbursementRequest.setEditLock(false);
		return disbursementRequestRepo.save(disbursementRequest);
	}
	
	/**
	 * 
	 * @param disbursementRequestData
	 * @return disbursementHistory
	 */
	private DisbursementHistory setDisbursementHistoryData(DisbursementRequest disbursementRequestData) {
		DisbursementHistory disbursementHistory = new DisbursementHistory();
		disbursementHistory.setTransactionId(disbursementRequestData.getTransactionId());
		disbursementHistory.setApplicationNumber(disbursementRequestData.getApplicationNumber());
		disbursementHistory.setEarlierDisbAmt(disbursementRequestData.getEarlierDisbAmt());
		disbursementHistory.setDisbAmt(disbursementRequestData.getDisbAmt());
		disbursementHistory.setTotalDisbAmt(disbursementRequestData.getTotalDisbAmt());
		disbursementHistory.setDateOfDisb(disbursementRequestData.getDateOfDisb());
		disbursementHistory.setBillingDay(disbursementRequestData.getBillingDay());
		disbursementHistory.setBillingDate(disbursementRequestData.getBillingDate());
		disbursementHistory.setEmiCommDate(disbursementRequestData.getEmiCommDate());
		disbursementHistory.setFirstEmiDueDate(disbursementRequestData.getFirstEmiDueDate());
		disbursementHistory.setRequestStatus(disbursementRequestData.getRequestStatus());
		disbursementHistory.setPaymentMode(disbursementRequestData.getPaymentMode());
		disbursementHistory.setShflBank(disbursementRequestData.getShflBank());
		disbursementHistory.setRemarks(disbursementRequestData.getRemarks());
		return disbursementHistoryRepo.save(disbursementHistory);
	}
	

	/**
	 * 
	 * @param disbursementModel
	 * @return disbursementFavoursList
	 */
	private List<DisbursementFavour> setDisbursementFavourData(DisbursementModel disbursementModel) {
		List<DisbursementFavour> disbursementFavoursList=new ArrayList<>();
		disbursementModel.getDisbursementFavours().stream().forEach(favour->{
			DisbursementFavour disbursementFavour = new DisbursementFavour();
			disbursementFavour.setBankAccNumber(favour.getBankAccNumber());
			disbursementFavour.setApplicationNumber(disbursementModel.getApplicationNumber());
			disbursementFavour.setDistNo(disbursementModel.getDisbNo());
			disbursementFavour.setDisbAmount(favour.getDisbAmount());
			disbursementFavoursList.add(disbursementFavour);
		});
		return disbursementFavourRepo.saveAll(disbursementFavoursList);	
	}
	
	/**
	 * 
	 * @param disbursementRequestData
	 * @param disbursementFavourDataList
	 * @return disbursementModel
	 */
	private DisbursementModel getDisbursementModelData(DisbursementRequest disbursementRequestData,
			List<DisbursementFavour> disbursementFavourDataList) {
		DisbursementModel disbursementModel = new DisbursementModel();
		disbursementModel.setDisbRequestId(disbursementRequestData.getDisbRequestId());
		disbursementModel.setApplicationNumber(disbursementRequestData.getApplicationNumber());
		disbursementModel.setTransactionId(disbursementRequestData.getTransactionId());
		disbursementModel.setEarlierDisbAmt(disbursementRequestData.getEarlierDisbAmt());
		disbursementModel.setDisbAmt(disbursementRequestData.getDisbAmt());
		disbursementModel.setDisbNo(disbursementRequestData.getDisbNo());
		disbursementModel.setTotalDisbAmt(disbursementRequestData.getTotalDisbAmt());
		disbursementModel.setDateOfDisb(disbursementRequestData.getDateOfDisb());
		disbursementModel.setBillingDay(disbursementRequestData.getBillingDay());
		disbursementModel.setBillingDate(disbursementRequestData.getBillingDate());
		disbursementModel.setEmiCommDate(disbursementRequestData.getEmiCommDate());
		disbursementModel.setFirstEmiDueDate(disbursementRequestData.getFirstEmiDueDate());
		disbursementModel.setEffectiveDate(disbursementRequestData.getEffectiveDate());
		disbursementModel.setRequestStatus(disbursementRequestData.getRequestStatus());
		disbursementModel.setPaymentMode(disbursementRequestData.getPaymentMode());
		disbursementModel.setShflBank(disbursementRequestData.getShflBank());
		disbursementModel.setRemarks(disbursementRequestData.getRemarks());
		disbursementModel.setEditLock(false);
		List<DisbursementFavour> disbursementFavoursList = new ArrayList<>();
		disbursementFavourDataList.stream().forEach(data -> {
			DisbursementFavour disbursementFavour = new DisbursementFavour();
			disbursementFavour.setApplicationNumber(data.getApplicationNumber());
			disbursementFavour.setBankAccNumber(data.getBankAccNumber());
			disbursementFavour.setDistNo(data.getDistNo());
			disbursementFavour.setDisbAmount(data.getDisbAmount());
			disbursementFavoursList.add(disbursementFavour);
		});
		disbursementModel.setDisbursementFavours(disbursementFavoursList);
		return disbursementModel;
	}	

}
