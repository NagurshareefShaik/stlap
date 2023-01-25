package shfl.st.lap.reports.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import shfl.st.lap.employee.model.DisbursmentCurrent;
import shfl.st.lap.employee.model.DisbursmentProcess;
import shfl.st.lap.repaymentschedule.model.AmortResposnseModel;
import shfl.st.lap.repaymentschedule.service.RepaymentService;

@Service
@AllArgsConstructor
public class ReportService {

	private ResourceLoader resourceLoader;
	
	private RepaymentService repaymentService;

	public ResponseEntity<byte[]> generateCustomerReport() throws Exception {
		DisbursmentProcess disbursmentProcess=getDisbursmentData();
		try {
			System.out.println(disbursmentProcess);
			System.out.println("genereate report method started");
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(disbursmentProcess.getDisbursmentCurrent());
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("disbursmentTable", beanCollectionDataSource);
			parameters.put("apllicantName", disbursmentProcess.getApplicantName());
			parameters.put("loanRequestDate", disbursmentProcess.getLoanRequestDate());
			parameters.put("totalDisbursmentAmt", disbursmentProcess.getTotalDisbursmentAmt());
			parameters.put("numberOfDisbursment", disbursmentProcess.getNumberOfDisbursment());
			parameters.put("currentDisbursment", disbursmentProcess.getCurrentDisbursment());
			parameters.put("effectiveRate", disbursmentProcess.getEffectiveRate());
			parameters.put("proposalType", disbursmentProcess.getProposalType());
			parameters.put("sanctionRate", disbursmentProcess.getSanctionDate());
			parameters.put("fileNumber", disbursmentProcess.getFileNumber());
			parameters.put("dateOfDisbursment", disbursmentProcess.getDateOfDisbursment());
			parameters.put("paymentMode", disbursmentProcess.getPaymentMode());
			parameters.put("chequeMode", disbursmentProcess.getChequeMode());
			parameters.put("chequePrintAt", disbursmentProcess.getChequePrintAt());
			parameters.put("entityName", disbursmentProcess.getEntityName());
			parameters.put("favourName", disbursmentProcess.getFavourName());
			parameters.put("accountNumber", disbursmentProcess.getAccountNumber());
			parameters.put("debitAccountDetail", disbursmentProcess.getDebitAccountDetail());
			parameters.put("IfscCode", disbursmentProcess.getIfscCode());
			parameters.put("imageDir", resourceLoader.getResource("classpath:images").getURI().getPath());
			HttpHeaders headers = new HttpHeaders();
			// set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "Disbursment Report.pdf");
			String path = resourceLoader.getResource("classpath:jrxml/disbursmentReport.jrxml").getURI().getPath();
			JasperReport jasperReport = JasperCompileManager.compileReport(path);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			System.out.println("genereate report method completed");
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers,
					HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private DisbursmentProcess getDisbursmentData() {
		DisbursmentProcess process=new DisbursmentProcess();
		DisbursmentCurrent disbursment=new DisbursmentCurrent();
		disbursment.setId(1);
		disbursment.setAmount(10000);
		disbursment.setAccountNumber("182728928282");
		disbursment.setEmiType("Fixed Amount");
		disbursment.setIfscCode("HDFC000007");
		disbursment.setPaymentMode("Cash");
		disbursment.setEntityName("Tom");
		
		DisbursmentCurrent disbursment1=new DisbursmentCurrent();
		disbursment1.setId(1);
		disbursment1.setAmount(10000);
		disbursment1.setAccountNumber("182728928282");
		disbursment1.setEmiType("Fixed Amount");
		disbursment1.setIfscCode("HDFC000007");
		disbursment1.setPaymentMode("Cash");
		disbursment1.setEntityName("Tom");
		
		process.setAccountNumber("1242112176865264");
		process.setApplicantName("Sundaram");
		process.setChequeMode("Crossed Cheque");
		process.setChequePrintAt("UnKnown");
		process.setCurrentDisbursment(500000);
		process.setDateOfDisbursment("08/18/2014");
		process.setDebitAccountDetail("3456789976");
		//list
		process.setDisbursmentCurrent(Arrays.asList(disbursment,disbursment1));
		process.setEffectiveRate("18");
		process.setFavourName("Sundaram Finance");
		process.setFileNumber("STLAP123456");
		process.setIfscCode("HDFC000500");
		process.setLoanRequestDate("08/18/2014");
		process.setNumberOfDisbursment(2);
		process.setPaymentMode("Cash");
		process.setProposalType("Agri Land");
		process.setSanctionDate("08/18/2014");
		process.setEntityName("Sundaram Home");
		process.setIfscCode("HDFC000500");
		process.setTotalDisbursmentAmt(500000);
		return process;
	}
	
	public ResponseEntity<byte[]> generateRepaySchedule(Map<String,String> appMap) throws IOException, JRException {
		AmortResposnseModel amortResposnseModel=repaymentService.calculateRepaymentSchedule(appMap);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(amortResposnseModel.getAmortModelList());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("repaymentParameter", beanCollectionDataSource);
		parameters.put("customerName", amortResposnseModel.getCustomerName());
		parameters.put("loanNumber", amortResposnseModel.getApplicationNum());
		parameters.put("tenure", amortResposnseModel.getTenure());
		parameters.put("loanType", "LAP");
		parameters.put("amountFinanced", amortResposnseModel.getSanctionAmount());
		parameters.put("frequency", amortResposnseModel.getFrequency());
		parameters.put("imageDir", resourceLoader.getResource("classpath:images").getURI().getPath());
		parameters.put("totalInterest", amortResposnseModel.getTotalInterest());
		parameters.put("totalPrincipal", amortResposnseModel.getTotalAmount());
		parameters.put("totalAmount", amortResposnseModel.getTotalInterest()+amortResposnseModel.getTotalAmount());
		HttpHeaders headers = new HttpHeaders();
		// set the PDF format
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "Repayment Schedule-"+amortResposnseModel.getApplicationNum()+".pdf");
		String path = resourceLoader.getResource("classpath:jrxml/loanRepaymentSchedule.jrxml").getURI().getPath();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		System.out.println("genereate report method completed");
		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers,
				HttpStatus.OK);
	}

}
