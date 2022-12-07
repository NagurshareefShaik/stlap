package shfl.st.lap.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import shfl.st.lap.model.DisbursmentProcess;

@Service
public class ReportService {

	@Autowired
	ResourceLoader resourceLoader;

	public ResponseEntity<byte[]> generateCustomerReport(DisbursmentProcess disbursmentProcess) throws Exception {
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
			parameters.put("effectiveDate", disbursmentProcess.getEffectiveDate());
			parameters.put("proposalType", disbursmentProcess.getProposalType());
			parameters.put("sanctionDate", disbursmentProcess.getSanctionDate());
			parameters.put("fileNumber", disbursmentProcess.getFileNumber());
			parameters.put("dateOfDisbursment", disbursmentProcess.getDateOfDisbursment());
			parameters.put("paymentMode", disbursmentProcess.getPaymentMode());
			parameters.put("chequeMode", disbursmentProcess.getChequeMode());
			parameters.put("chequePrintAt", disbursmentProcess.getChequePrintAt());
			parameters.put("entityName", disbursmentProcess.getEntityName());
			parameters.put("favourName", disbursmentProcess.getFavourName());
			parameters.put("accountNumber", disbursmentProcess.getAccountNumber());
			parameters.put("debitAccountDetail", disbursmentProcess.getDebitAccountDetail());
			parameters.put("ifscCode", disbursmentProcess.getIfscCode());
			parameters.put("imageDir", resourceLoader.getResource("classpath:images").getURI().getPath());
			HttpHeaders headers = new HttpHeaders();
			// set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "Disbursment Report.pdf");
			String path = resourceLoader.getResource("classpath:jrxml/disbursmentReport.jrxml").getURI().getPath();
			JasperReport jasperReport = JasperCompileManager.compileReport(path);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			System.out.println("genereate report method compled");
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers,
					HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println("exception occured in report service");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
