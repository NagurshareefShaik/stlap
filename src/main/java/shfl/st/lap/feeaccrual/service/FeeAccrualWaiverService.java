package shfl.st.lap.feeaccrual.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.feeaccrual.model.AdditionalFees;
import shfl.st.lap.feeaccrual.model.AdditionalFeesDescription;
import shfl.st.lap.feeaccrual.repo.AdditionalFeesRepo;
import shfl.st.lap.feeaccrual.repo.FeeAccrualWaiverRepo;

@Service
public class FeeAccrualWaiverService {
	@Autowired
	FeeAccrualWaiverRepo feeAccrualWaiverRepo;

	@Autowired
	AdditionalFeesRepo additionalFeesRepo;

	public ResponseEntity<String> saveFeeDetails(Map<String, Object> dataMap) {
		List<Map<String, Object>> gridData = (List<Map<String, Object>>) dataMap.get("gridData");
		List<AdditionalFeesDescription> saveData = new ArrayList<>();
		gridData.stream().forEach(data -> {
			AdditionalFeesDescription additionalFeeDescription = new AdditionalFeesDescription();
			additionalFeeDescription.setApplicationNumber(getString(dataMap.get("applicationNumber")));
			additionalFeeDescription.setReferenceNumber(getString(dataMap.get("referenceNumber")));
			additionalFeeDescription.setFeeDescription(getString(data.get("details")));
			additionalFeeDescription.setEarlierWaiver(getInt(data.get("earlyWaived")));
			additionalFeeDescription.setDeductions(getInt(data.get("outstanding")));
			additionalFeeDescription.setReceivable(getInt(data.get("receiveable")));
			additionalFeeDescription.setReceived(getInt(data.get("received")));
			saveData.add(additionalFeeDescription);
		});
		feeAccrualWaiverRepo.saveAll(saveData);
		saveOtherDetails(dataMap);
		return ResponseEntity.ok().body("saved");
	}

	private void saveOtherDetails(Map<String, Object> dataMap) {
		AdditionalFees additionalFeesEntity = new AdditionalFees();
		additionalFeesEntity.setApplicationNumber(getString(dataMap.get("applicationNumber")));
		additionalFeesEntity.setReferenceNo(getString(dataMap.get("referenceNumber")));
		additionalFeesEntity.setAddtnlFeeDedType(getString(dataMap.get("type")));
		additionalFeesEntity.setEditLock(true);
		additionalFeesEntity.setRemarks(getString(dataMap.get("remarks")));
		additionalFeesEntity.setReason(getString(dataMap.get("reason")));
		additionalFeesEntity.setCreatedBy(getString(dataMap.get("updatedBy")));
		additionalFeesEntity.setModifiedBy(getString(dataMap.get("updatedBy")));
//		additionalFeesEntity.setReferenceDate(getString(dataMap.get("refDate")));
		additionalFeesRepo.save(additionalFeesEntity);
	}

	public ResponseEntity<Map<String, Object>> getFeeData(Map<String, Object> dataMap) {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> otherDetailMap = new HashMap<>();
		String applicationNumber = getString(dataMap.get("applicationNumber"));
		AdditionalFees otherDetails = additionalFeesRepo.findByApplicationNumber(applicationNumber);
		List<AdditionalFeesDescription> resultData = feeAccrualWaiverRepo.findByApplicationNumber(applicationNumber);
		List<Map<String, Object>> feeDataList = new ArrayList<>();
		resultData.stream().forEach(feeData -> {
			Map<String, Object> feeMap = new HashMap<>();
			feeMap.put("id", feeData.getFeeDescription());
			feeMap.put("details", feeData.getFeeDescription());
			feeMap.put("receiveable", feeData.getReceivable());
			feeMap.put("received", feeData.getReceived());
//			feeMap.put("due", feeData.getOutstAmount());
			feeMap.put("additionalWaiver", 0);
			feeMap.put("earlyWaiver", feeData.getEarlierWaiver());
			feeDataList.add(feeMap);
		});
		otherDetailMap.put("reason", otherDetails.getReason());
		otherDetailMap.put("remark", otherDetails.getRemarks());
		otherDetailMap.put("referenceNumber", otherDetails.getReferenceNo());
		otherDetailMap.put("updatedBy", otherDetails.getCreatedBy());
		returnMap.put("gridData", feeDataList);
		returnMap.put("otherList", otherDetailMap);
		return ResponseEntity.ok().body(returnMap);
	}

	private Integer getInt(Object object) {

		return (object == null || object.toString().equals("")) ? 0 : Integer.parseInt(object.toString());
	}

	public String getString(Object data) {
		return data != null ? data.toString() : "";
	}
}