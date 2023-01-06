package shfl.st.lap.dashboard.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;
import shfl.st.lap.feeaccrual.model.AdditionalFeesDescription;
import shfl.st.lap.feeaccrual.repo.FeeAccrualWaiverRepo;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;


@Service
public class StlapDashboardService {
	@Autowired
	DisbursementRequestRepo disbursementRequestRepo;
	
	@Autowired
	LosCustomerRepo losCustomerRepo;
	
	@Autowired
	FeeAccrualWaiverRepo feeAccrualWaiverRepo;
	
	public ResponseEntity<Map<String,Object>> getDashBoardData(Map<String, Object> datamap) {
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		//need to change number to get required month
	    int month = 0;
	    int day = 1;
	    c.set(year, month, day);
	    int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    Date startDate=c.getTime();
	    c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
	    Date endDate=c.getTime();
		
		List<DisbursementRequest> disbReqList=disbursementRequestRepo.findAll();
		Double approvedAmount=disbReqList.stream().filter(disb->disb.getRequestStatus().equalsIgnoreCase("approved"))
				.map(paid->paid.getDisbAmt()).collect(Collectors.summingDouble(Float::floatValue));
		
	    
		List<LosCustomer> losCustomerList=losCustomerRepo.findAll();
		Map<java.sql.Date, List<DisbursementRequest>> disbursmentData = disbReqList.stream().filter(disb->disb.getRequestStatus().equalsIgnoreCase("requested"))
				.collect(Collectors.groupingBy(DisbursementRequest::getDateOfDisb));
		List<LosCustomer> monthCustomerList=losCustomerList.stream().filter(cust->cust.getCreatedDate().after(startDate))
				.filter(cust->cust.getCreatedDate().before(endDate)).collect(Collectors.toList());
		System.out.println(monthCustomerList.size());
		Map<Integer,Integer>yearData = new HashMap<>();
		Set<java.sql.Date>dataList=disbursmentData.keySet();
		disbursmentData.forEach((key,value)->{
			int currentCount = Objects.isNull(yearData.get(key.getMonth()))?0:yearData.get(key.getMonth());
			yearData.put(key.getMonth(),currentCount+value.size());
		});
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("sanctioned", losCustomerRepo.count());
		returnMap.put("requested", getStatusCount("requested",disbReqList));
		returnMap.put("approved", getStatusCount("approved",disbReqList));
		returnMap.put("cancelled", getStatusCount("CANCEL",disbReqList));
		returnMap.put("approvedAmount", approvedAmount);
		returnMap.put("oneMonth", getOneMonthData(disbursmentData));
		returnMap.put("oneYear", getOneYearData(yearData));
		
		Set<String> listOfApplicationNumber = losCustomerList.stream().map(map-> map.getApplicationNumber()).collect(Collectors.toSet());
		if(feeAccrualWaiverRepo.count()==0) {
			insertData(listOfApplicationNumber);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	private void insertData(Set<String> listOfApplicationNumber) {
		List<Map<String,Object>>dataMAP=formListMap();
		List<AdditionalFeesDescription>saveData = new ArrayList<>();
		listOfApplicationNumber.stream().forEach(appNum->{
			dataMAP.stream().forEach(data->{
				AdditionalFeesDescription additionalFeeDescription = new AdditionalFeesDescription();
				additionalFeeDescription.setApplicationNumber(appNum);
				additionalFeeDescription.setFeeDescription(String.valueOf(data.get("description")));
				additionalFeeDescription.setEarlierWaiver(0);
				additionalFeeDescription.setAdditionalAccrual(0);
				additionalFeeDescription.setDeductions(0);
				additionalFeeDescription.setReceivable(Integer.parseInt(data.get("value").toString()));
				additionalFeeDescription.setReceived(0);
				saveData.add(additionalFeeDescription);
			});
		});
		feeAccrualWaiverRepo.saveAll(saveData);
		}
	private List<Map<String, Object>> formListMap() {
		List<Map<String,Object>> tempMap = new ArrayList<>();
//		for(int i =0;i>10;i++) {
			Map<String,Object> innerMap = new HashMap<>();
			innerMap.put("description", "Mod Charges");
			innerMap.put("value", 5000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Legal Charges");
			innerMap.put("value", 7000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("Technical Assistance Charges", "Fee");
			innerMap.put("value", 3000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Documentation Charges");
			innerMap.put("value", 25000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "File Processing Charges");
			innerMap.put("value", 1000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Application Fee");
			innerMap.put("value", 8000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Prepayment Charge");
			innerMap.put("value", 1000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Partial prepayment charge");
			innerMap.put("value", 10000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Late Fee charge");
			innerMap.put("value", 500);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Recovery Charge");
			innerMap.put("value", 5000);
			tempMap.add(innerMap);
			innerMap = new HashMap<>();
			innerMap.put("description", "Insurance Premium Charge");
			innerMap.put("value", 7000);
			tempMap.add(innerMap);
//		}
		return tempMap;
	
	}

	public long getStatusCount(String status, List<DisbursementRequest> disbList) {
		return disbList.stream().filter(disb->disb.getRequestStatus().equalsIgnoreCase(status)).count();
	}

	private List<Map<String,Object>> getOneYearData(Map<Integer, Integer> yearData) {
		List<Map<String,Object>> oneYearData = new ArrayList<>();
		String startMonth = "JAN";
		String endMonth = "JAN";
		int startYear = 2023;
		int endYear = 2024;
		List<String>monthList=getMonthList(startMonth, endMonth, startYear, endYear);
		int daysInMonth = monthList.size();
		for (int month = 0; month < daysInMonth; month++) {
			Map<String,Object> dayValueMap = new HashMap<>();
			dayValueMap.put("name", monthList.get(month));
			dayValueMap.put("sanctioned", yearData.get(month));
			oneYearData.add(dayValueMap);
		}
		return oneYearData;
	}

	private List<String> getMonthList(String startMonth,String endMonth,int startYear,int endYear) {
		List<String> monthList = new ArrayList<>();
		 String date1 = startMonth+"-"+(startYear);
	     String date2 = endMonth+"-"+(endYear);

	     DateFormat formater = new SimpleDateFormat("MMM-YYYY");

	     Calendar beginCalendar = Calendar.getInstance();
	     Calendar finishCalendar = Calendar.getInstance();

	     try {
	         beginCalendar.setTime(formater.parse(date1));
	         finishCalendar.setTime(formater.parse(date2));
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }

	     while (beginCalendar.before(finishCalendar)) {
	         monthList.add(formater.format(beginCalendar.getTime()).toUpperCase().substring(0, 3));
	         beginCalendar.add(Calendar.MONTH, 1);
	     }
		return monthList;
	}

	private List<Map<String,Object>> getOneMonthData(Map<java.sql.Date, List<DisbursementRequest>> disbursmentData) {
		List<Map<String,Object>> oneMonthData = new ArrayList<>();
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH)+1;
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int date = 1; date <= daysInMonth; date++) {
			Map<String,Object> dayValueMap = new HashMap<>();
			dayValueMap.put("name", date);
			dayValueMap.put("sanctioned", findDayWiseDataCount(disbursmentData,date,month,year));
			oneMonthData.add(dayValueMap);
		}
		return oneMonthData;
	}

	private int findDayWiseDataCount(Map<java.sql.Date, List<DisbursementRequest>> disbursmentData, int date, int month, int year) {
		List<DisbursementRequest> dayWiseData = disbursmentData.get(new java.sql.Date(year-1900,month-1,date));
		return Objects.isNull(dayWiseData)?0:dayWiseData.size();
	}
	


	
}