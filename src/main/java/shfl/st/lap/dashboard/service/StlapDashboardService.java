package shfl.st.lap.dashboard.service;


import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;


@Service
public class StlapDashboardService {
	@Autowired
	DisbursementRequestRepo disbursementRequestRepo;
	
	@Autowired
	shfl.st.lap.loscustomer.repo.LosCustomerRepo losCustomerRepo;

	public ResponseEntity<Map<String,Object>> getDashBoardData(Map<String, Object> datamap) {
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("sanctioned", losCustomerRepo.count());
		returnMap.put("requested", disbursementRequestRepo.count());
		returnMap.put("approved", 0);
		returnMap.put("approvedAmount", Integer.valueOf(20000));
		returnMap.put("oneMonth", getOneMonthData());
		returnMap.put("oneYear", getOneYearData());
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	private List<Map<String,Object>> getOneYearData() {
		List<Map<String,Object>> oneYearData = new ArrayList<>();
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH);
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int date = 1; date <= daysInMonth; date++) {
			Map<String,Object> dayValueMap = new HashMap<>();
			dayValueMap.put("name", date);
			dayValueMap.put("uv", date);
			dayValueMap.put("pv", date);
			dayValueMap.put("amt", date);
			oneYearData.add(dayValueMap);
		}
		return oneYearData;
	}

	private List<Map<String,Object>> getOneMonthData() {
		List<Map<String,Object>> oneMonthData = new ArrayList<>();
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH);
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int date = 1; date <= daysInMonth; date++) {
			Map<String,Object> dayValueMap = new HashMap<>();
			dayValueMap.put("name", date);
			dayValueMap.put("uv", date);
			dayValueMap.put("pv", date);
			dayValueMap.put("amt", date);
			oneMonthData.add(dayValueMap);
		}
		return oneMonthData;
	}
	


	
}