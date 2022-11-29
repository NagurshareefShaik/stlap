package shfl.st.lap.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Collections;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import shfl.st.lap.model.LoanDetails;
import shfl.st.lap.model.LoanRepayment;

@Service
public class ReportService {

	
	private static final String CHARGES = "charges";
	
	@Autowired
	ResourceLoader resourceLoader;
	
	

	public String generateCustomerReport(LoanDetails loanDetails) throws JRException, IOException {
		List<LoanRepayment> loanDetailsList=loanDetails.getLoanRepayment();
		JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(loanDetailsList);
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("repaymentParameter",beanCollectionDataSource);
		parameters.put("customerName",loanDetails.getCustomerName());
		parameters.put("loanNumber",loanDetails.getLoanNumber());
		parameters.put("tenure",loanDetails.getTenure());
		parameters.put("totalInstl",loanDetails.getTotalInstl());
		parameters.put("currency",loanDetails.getCurrency());
		parameters.put("loanType",loanDetails.getLoanType());
		parameters.put("amountFinanced",loanDetails.getAmountFinanced());
		parameters.put("frequency",loanDetails.getFrequency());
		parameters.put("sample","sample valueto demg");
		String path = resourceLoader.getResource("classpath:jrxml/loanRepaymentSchedule.jrxml").getURI().getPath();
		JasperReport jasperReport=JasperCompileManager.compileReport(path);
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\nagubash\\Desktop\\Loan Repayment Schedule.pdf");
		return "Report generated successfully";
	}
	public String generateMonthDueReport(LoanDetails loanDetails) throws JRException, IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 5);
		Date date = cal.getTime();
		Map<String,Object> transactionMap=new HashMap<>();
		transactionMap.put("date",date);
		transactionMap.put("description","loan payment monthly");
		transactionMap.put(CHARGES,0);
		transactionMap.put("payment",8607);
		Map<String,Object> transactionMap1=new HashMap<>();
		transactionMap1.put("date",date);
		transactionMap1.put("description","charges for property inspection");
		transactionMap1.put(CHARGES,250);
		transactionMap1.put("payment",0);
		JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(Arrays.asList(transactionMap,transactionMap1));
		List<LoanRepayment> loanDetailsList=loanDetails.getLoanRepayment();
		Stream<LoanRepayment> nextMonthDueStream=loanDetailsList.stream().filter(loan->loan.getDuedate().after(new Date()));
		LoanRepayment nextMonthDue=new LoanRepayment();
		if(!Collections.isEmpty(loanDetailsList)) {
			nextMonthDue=nextMonthDueStream.findFirst().get();
		}
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("transactionDetailsParameter",beanCollectionDataSource);
		parameters.put("accountNumber",loanDetails.getLoanNumber());
		parameters.put("paymentDueDate",nextMonthDue.getDuedate());
		parameters.put("amountDue",nextMonthDue.getPrincipal()+nextMonthDue.getInterest());
		parameters.put("oustandingPrincipal",nextMonthDue.getOutstandamt());
		parameters.put("interestRate",nextMonthDue.getInterest());
		parameters.put(CHARGES,0);
		parameters.put("principal",nextMonthDue.getPrincipal());
		int monthDue=nextMonthDue.getPrincipal()+nextMonthDue.getInterest()+Integer.parseInt(parameters.get(CHARGES).toString());
		parameters.put("monthlyPayment",monthDue);
		parameters.put("totalDue",monthDue);
		//data for
		parameters.put("pastMonthPrincipal",8107);
		parameters.put("pastMonthInterest",500);
		parameters.put("pastMonthTotalAmount",8607);
		parameters.put("yearToDatePrincipal",8107);
		parameters.put("yearToDateInterest",500);
		parameters.put("yearToDateTotalAmount",8607);
		parameters.put("customerName",loanDetails.getCustomerName());
		parameters.put("tenure",12);
		parameters.put("interestPercentage","6");
		String path = resourceLoader.getResource("classpath:jrxml/reportForMonthlyDue.jrxml").getURI().getPath();
		JasperReport jasperReport=JasperCompileManager.compileReport(path);
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\nagubash\\Desktop\\reportForMonthlyDue.pdf");
		return "Report generated successfully";
	}
}
