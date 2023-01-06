package shfl.st.lap.feeaccrual.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.feeaccrual.model.AdditionalFees;
import shfl.st.lap.feeaccrual.model.AdditionalFeesDescription;
import shfl.st.lap.feeaccrual.model.AdditionalFeesHistory;
import shfl.st.lap.feeaccrual.repo.AdditionalFeesRepo;
import shfl.st.lap.feeaccrual.repo.FeeAccrualWaiverRepo;
import shfl.st.lap.feeaccrual.repo.FeeHistoryRepo;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;

@Service
public class FeeAccrualWaiverService {
	@Autowired
	FeeAccrualWaiverRepo feeAccrualWaiverRepo;
	
	@Autowired
	AdditionalFeesRepo additionalFeesRepo;
	
	@Autowired
	FeeHistoryRepo feeHistoryRepo;
	
	@Autowired
	LosCustomerRepo losCustomerRepo;

	public ResponseEntity<String> saveFeeDetails(Map<String, Object> dataMap) {
		List<Map<String, Object>> gridData = (List<Map<String, Object>>)dataMap.get("gridData");
		String type = getString(dataMap.get("type"));
		Integer refNumber = getInt(dataMap.get("referenceNumber"));
		String refDate = getString(dataMap.get("refDate"));
		String applicationNumber = getString(dataMap.get("applicationNumber"));
		String userName = getString(dataMap.get("updatedBy"));
		List<AdditionalFeesDescription> saveData = new ArrayList<>();
		gridData.stream().forEach(data->{
			AdditionalFeesDescription additionalFeeDescription = new AdditionalFeesDescription();
			additionalFeeDescription.setApplicationNumber(applicationNumber);
			additionalFeeDescription.setFeeDescription(getString(data.get("details")));
			additionalFeeDescription.setEarlierWaiver(getInt(data.get("earlyWaiver"))+getInt(data.get("additionalWaiver")));
			additionalFeeDescription.setAdditionalAccrual(getInt(data.get("additionalAccrual")));
			additionalFeeDescription.setDeductions(getInt(data.get("outstanding")));
			if("accrual".equals(type)) {
				additionalFeeDescription.setReceivable(getInt(data.get("receiveable"))+getInt(data.get("additionalAccrual")));
			}else {
				additionalFeeDescription.setReceivable(getInt(data.get("receiveable")));
			}
			additionalFeeDescription.setReceived(getInt(data.get("received")));
			saveData.add(additionalFeeDescription);
		});
		feeAccrualWaiverRepo.saveAll(saveData);
		saveOtherDetails(dataMap);
		saveHistoryData(dataMap,type,applicationNumber,refNumber,refDate,userName);
		return ResponseEntity.ok().body("saved");
	}
	
	private void saveHistoryData(Map<String, Object> dataMap, String type, String applicationNumber, Integer refNumber, String refDate, String userName) {
		Map<String,Object> historyData = (Map<String, Object>) dataMap.get("historyData");
		LocalDateTime time = LocalDateTime.now();
		List<AdditionalFeesHistory> saveData = new ArrayList<>();
		historyData.forEach((key,value)->{
			AdditionalFeesHistory history = new AdditionalFeesHistory();
			history.setApplicationNumber(applicationNumber);
			history.setFeeType(type);
			history.setModifiedBy(userName);
			history.setModifiedDateTime(time);
			history.setModifiedValue(value.toString());
			history.setModifiedContent(key);
			history.setReferenceNo(getInt(refNumber));
			history.setReferenceDate(refDate);
			saveData.add(history);
		});
		feeHistoryRepo.saveAll(saveData);
	}

	private void saveOtherDetails(Map<String, Object> dataMap) {
		AdditionalFees additionalFeesEntity = new AdditionalFees();
		additionalFeesEntity.setApplicationNumber(getString(dataMap.get("applicationNumber")));
		additionalFeesEntity.setReferenceNo(getInt(dataMap.get("referenceNumber")));
		additionalFeesEntity.setFeeType(getString(dataMap.get("type")));
		additionalFeesEntity.setEditLock(true);
		additionalFeesEntity.setRemarks(getString(dataMap.get("remarks")));
		additionalFeesEntity.setReason(getString(dataMap.get("reason")));
		additionalFeesEntity.setCreatedBy(getString(dataMap.get("updatedBy")));
		additionalFeesEntity.setModifiedBy(getString(dataMap.get("updatedBy")));
		additionalFeesEntity.setReferenceDate(getString(dataMap.get("refDate")));
		additionalFeesEntity.setCreatedDateTime(LocalDateTime.now());
		additionalFeesRepo.save(additionalFeesEntity);
	}

	public ResponseEntity<Map<String, Object>> getFeeData(Map<String, Object> dataMap) {
		Map<String,Object>returnMap = new HashMap<>();
		Map<String,Object>otherDetailMap = new HashMap<>();
		String applicationNumber = getString(dataMap.get("applicationNumber"));
		String type = getString(dataMap.get("type"));
		List<AdditionalFeesDescription>resultData=feeAccrualWaiverRepo.findByApplicationNumber(applicationNumber);
		List<AdditionalFees> otherDetailsTotalList = additionalFeesRepo.findByApplicationNumberAndFeeTypeOrderByReferenceNoDesc(applicationNumber,type);
		if(!otherDetailsTotalList.isEmpty()) {
			AdditionalFees otherDetails = otherDetailsTotalList.get(0);
			otherDetailMap.put("reason", otherDetails.getReason());
			otherDetailMap.put("remark", otherDetails.getRemarks());
			otherDetailMap.put("referenceNumber", otherDetails.getReferenceNo());
			otherDetailMap.put("updatedBy", otherDetails.getCreatedBy());
		}
		List<Map<String,Object>>feeDataList = new ArrayList<>();
		resultData.stream().forEach(feeData->{
			Map<String,Object>feeMap = new HashMap<>();
			feeMap.put("id", feeData.getFeeDescription());
			feeMap.put("details", feeData.getFeeDescription());
			feeMap.put("receiveable", feeData.getReceivable());
			feeMap.put("received", feeData.getReceived());
			feeMap.put("due", feeData.getOutstAmount());
			feeMap.put("additionalWaiver", 0);
			feeMap.put("additionalAccrual", 0);
			feeMap.put("earlyWaiver", feeData.getEarlierWaiver());
			feeDataList.add(feeMap);
		});
		returnMap.put("gridData", feeDataList);
		returnMap.put("otherList", otherDetailMap);
		return ResponseEntity.ok().body(returnMap);
	}
	
	private Integer getInt(Object object) {
		return (object==null||object.toString().equals(""))?0:Integer.parseInt(object.toString());
	}

	public String getString(Object data) {
		return data!=null?data.toString():"";
	}

	public ResponseEntity<Map<String, Object>> getHistoryData(Map<String, Object> dataMap) {
		Map<String,Object>returnMap = new HashMap<>();
		List<Map<String,Object>> historyList = new ArrayList<>();
		String applicationNumber = getString(dataMap.get("applicationNumber"));
		String feeType = getString(dataMap.get("feeType"));
		AtomicInteger count = new AtomicInteger(0);
		List<AdditionalFeesHistory> fetchedData = feeHistoryRepo.findByApplicationNumberAndFeeTypeOrderByModifiedDateTimeDesc(applicationNumber,feeType);
		fetchedData.stream().forEach(value->{
			Map<String,Object> valueMap = new HashMap<>();
			valueMap.put("id", count.getAndIncrement());
			valueMap.put("modifiedBy", value.getModifiedBy());
			valueMap.put("modifiedContent", value.getModifiedContent());
			valueMap.put("modifiedValue", value.getModifiedValue());
			valueMap.put("refNum", value.getReferenceNo());
			valueMap.put("modifiedDate", value.getModifiedDateTime());
			historyList.add(valueMap);
		});
		returnMap.put("historyData", historyList);
		return ResponseEntity.ok().body(returnMap);
	}

	public ResponseEntity<List<Map<String, String>>> getApplicationNumber(Map<String, Object> datamap) {
		List<Map<String,String>>branchList = new ArrayList<>();
		List<LosCustomer> listOfCustomer = losCustomerRepo.findByBranch(getString(datamap.get("branchName")));
		listOfCustomer.stream().forEach(branch->{
			Map<String,String> branchmap = new HashMap<>();
			branchmap.put("label", branch.getApplicationNumber());
			branchmap.put("value", branch.getApplicationNumber());
			branchList.add(branchmap);
		});
		return ResponseEntity.ok().body(branchList);
	}
}
