package shfl.st.lap.repaymentschedule.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import shfl.st.lap.disbursementrequest.model.DisbursementRequest;
import shfl.st.lap.disbursementrequest.repo.DisbursementRequestRepo;
import shfl.st.lap.loscustomer.model.LosCustomer;
import shfl.st.lap.loscustomer.repo.LosCustomerRepo;
import shfl.st.lap.repaymentschedule.model.AmortModel;
import shfl.st.lap.repaymentschedule.model.AmortResposnseModel;

@Service
@AllArgsConstructor
public class RepaymentService {
	
	private DisbursementRequestRepo disbursementRequestRepo;
	
	private LosCustomerRepo losCustomerRepo;

	public AmortResposnseModel calculateRepaymentSchedule(Map<String,String> appMap) {
		String applicationNum=appMap.get("applicationNum");
		AmortResposnseModel amortResposnseModel=new AmortResposnseModel();
		List<AmortModel> amortModelList=new ArrayList<>();
		Optional<LosCustomer> customerData=losCustomerRepo.findById(applicationNum);
		List<DisbursementRequest> disbRequestList=disbursementRequestRepo.findByApplicationNum(applicationNum);
		Double principalAmount=disbRequestList.stream().filter(disb->disb.getRequestStatus().equalsIgnoreCase("approved"))
		.collect(Collectors.summingDouble(DisbursementRequest::getDisbAmt));
		LosCustomer losCustomerData=customerData.get();
		int time = losCustomerData.getTenure();
		//yearly rate of interest
		double rateOfInterest = losCustomerData.getRateOfInterest();
		//monthly converted rate of interest
		rateOfInterest = rateOfInterest / (12 * 100);
		double totalInterestPaid = 0,totalPrincipalPaid = 0;
		double montlyPayment= (principalAmount*rateOfInterest*Math.pow(1+rateOfInterest,time))/(Math.pow(1+rateOfInterest,time)-1);
		amortResposnseModel.setApplicationNum(applicationNum);
		amortResposnseModel.setCustomerName(losCustomerData.getCustomerName().toUpperCase());
		amortResposnseModel.setEmiAmount(Math.round(montlyPayment));
		amortResposnseModel.setFrequency("Monthly");
		amortResposnseModel.setLoanType("LAP");
		amortResposnseModel.setMandateValidity(DateUtils.addMonths(new Date(), losCustomerData.getTenure()-1));
		amortResposnseModel.setSanctionAmount((int)Math.round(principalAmount));
		amortResposnseModel.setTenure(losCustomerData.getTenure());
		for(int month=0;month<time;month++) {
			AmortModel amortModel=new AmortModel();
			double montlyInterest = (rateOfInterest * principalAmount);
			totalInterestPaid=totalInterestPaid+montlyInterest;
			double amountPaid=(montlyPayment-montlyInterest);
			totalPrincipalPaid=totalPrincipalPaid+amountPaid;
			double newBalance=principalAmount-amountPaid;
			Date newDate = DateUtils.addMonths(new Date(), month);
			amortModel.setMonthNo(month+1);
			amortModel.setDuedate(newDate);
			amortModel.setOpeningAmt(Math.round(principalAmount));
			amortModel.setMonthlyAmt(Math.round(montlyPayment));
			amortModel.setPrincipal(Math.round(amountPaid));
			amortModel.setInterest(Math.round(montlyInterest));
			amortModel.setClosingAmt(Math.round((newBalance)));
			amortModel.setRepayAmt(0);
			amortModelList.add(amortModel);
			principalAmount=newBalance;
		}
		amortResposnseModel.setTotalAmount(Math.round(totalPrincipalPaid));
		amortResposnseModel.setTotalInterest(Math.round(totalInterestPaid));
		amortResposnseModel.setAmortModelList(amortModelList);
		return amortResposnseModel;
	}
	
}
