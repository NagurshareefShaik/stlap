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
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;


@Service
public class StlapDashboardService {
	@Autowired
	DisbursementRequestRepo disbursementRequestRepo;
	
	@Autowired
	LosCustomerRepo losCustomerRepo;
	
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
		Double approvedAmount=disbReqList.stream().filter(disb->disb.getRequestStatus().equals("REQUEST"))
				.map(paid->paid.getDisbAmt()).collect(Collectors.summingDouble(Float::floatValue));
		
	    
		List<LosCustomer> losCustomerList=losCustomerRepo.findAll();
		Map<java.sql.Date, List<DisbursementRequest>> disbursmentData = disbReqList.stream().filter(disb->disb.getRequestStatus().equals("REQUEST"))
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
		returnMap.put("requested", getStatusCount("REQUEST",disbReqList));
		returnMap.put("approved", getStatusCount("PAID",disbReqList));
		returnMap.put("cancelled", getStatusCount("CANCEL",disbReqList));
		returnMap.put("approvedAmount", approvedAmount);
		returnMap.put("oneMonth", getOneMonthData(disbursmentData));
		returnMap.put("oneYear", getOneYearData(yearData));
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	public long getStatusCount(String status, List<DisbursementRequest> disbList) {
		return disbList.stream().filter(disb->disb.getRequestStatus().equals(status)).count();
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