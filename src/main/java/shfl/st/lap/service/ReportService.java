package shfl.st.lap.service;

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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import shfl.st.lap.model.Details;

@Service
public class ReportService {

	@Autowired
	ResourceLoader resourceLoader;

	public ResponseEntity<byte[]> generateCustomerReport() throws JRException, IOException {
		try {
			Map<String,Object> tableData=new HashMap<>();
			tableData.put("id", 1);
			tableData.put("amount", 100000);
			tableData.put("paymentMode", "CASH");
			tableData.put("emiType", "Emi Type");
			tableData.put("entityName", "Sample");
			tableData.put("accountNumber", "234567");
			tableData.put("ifscCode", "123456");
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(tableData));
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("disbursmentTable", beanCollectionDataSource);
			parameters.put("logo", resourceLoader.getResource("classpath:images/sf.png").getURI().getPath());
			HttpHeaders headers = new HttpHeaders();
			// set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "Disbursment Report.pdf");
			String path = resourceLoader.getResource("classpath:jrxml/disbursmentReport.jrxml").getURI().getPath();
			JasperReport jasperReport = JasperCompileManager.compileReport(path);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
