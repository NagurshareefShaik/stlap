package shfl.st.lap.ledger.util;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import shfl.st.lap.ledger.model.LedgerStage;

@Component
public class LedgerData {
	
	public List<LedgerStage> getLedgerData(){
		Date d=new Date();
		LedgerStage bankDebit=new LedgerStage();
		bankDebit.setAccountingType("BRANCH");
		bankDebit.setReferenceType("FILE");
		bankDebit.setEffectiveDate(new Date());
		bankDebit.setTxnAccount("ST001");
		bankDebit.setTxnCode(0);
		LedgerStage bankCredit=new LedgerStage();
		bankCredit.setAccountingType("BRANCH");
		bankCredit.setReferenceType("FILE");
		bankCredit.setEffectiveDate(new Date());
		bankCredit.setTxnAccount("ST002");
		bankCredit.setTxnCode(1);
		LedgerStage corporateDebit=new LedgerStage();
		corporateDebit.setAccountingType("CORPORATE");
		corporateDebit.setReferenceType("BANK");
		corporateDebit.setEffectiveDate(new Date());
		corporateDebit.setTxnAccount("ST003");
		corporateDebit.setTxnCode(0);
		LedgerStage corporateCredit=new LedgerStage();
		corporateCredit.setAccountingType("CORPORATE");
		corporateCredit.setReferenceType("BANK");
		corporateCredit.setEffectiveDate(new Date());
		corporateCredit.setTxnAccount("ST004");
		corporateCredit.setTxnCode(1);
		return Arrays.asList(bankDebit,bankCredit,corporateDebit,corporateCredit);
		
	}
	
	public Map<String,String> getFeeDescriptionBankData(){
		Map<String,String> feeBankDetails=new HashMap<>();
		feeBankDetails.put("Application Fee", "ST005");
		feeBankDetails.put("Documentation Charges", "ST006");
		feeBankDetails.put("File Processing Charges", "ST007");
		feeBankDetails.put("Insurance Premium Charge", "ST008");
		feeBankDetails.put("Late Fee charge", "ST009");
		feeBankDetails.put("Legal Charges", "ST010");
		feeBankDetails.put("Mod Charges", "ST011");
		feeBankDetails.put("Partial prepayment charge", "ST012");
		feeBankDetails.put("Prepayment Charge", "ST013");
		feeBankDetails.put("Recovery Charge", "ST014");
		return feeBankDetails;
		
	}

}
