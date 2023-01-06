package shfl.st.lap.ledger.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import shfl.st.lap.ledger.model.LedgerStage;

public class LedgerData {
	
	public List<LedgerStage> getLedgerData(){
		Date d=new Date();
		LedgerStage bankDebit=new LedgerStage();
		bankDebit.setAccountingType("BRANCH");
		bankDebit.setReferenceType("FILE");
		bankDebit.setEffectiveDate(new Date());
		bankDebit.setTxnAccount("1234567");
		bankDebit.setTxnCode(0);
		LedgerStage bankCredit=new LedgerStage();
		bankCredit.setAccountingType("BRANCH");
		bankCredit.setReferenceType("FILE");
		bankCredit.setEffectiveDate(new Date());
		bankCredit.setTxnAccount("1234567");
		bankCredit.setTxnCode(1);
		LedgerStage corporateDebit=new LedgerStage();
		corporateDebit.setAccountingType("CORPORATE");
		corporateDebit.setReferenceType("BANK");
		corporateDebit.setEffectiveDate(new Date());
		corporateDebit.setTxnAccount("1234568");
		corporateDebit.setTxnCode(0);
		LedgerStage corporateCredit=new LedgerStage();
		corporateCredit.setAccountingType("CORPORATE");
		corporateCredit.setReferenceType("BANK");
		corporateCredit.setEffectiveDate(new Date());
		corporateCredit.setTxnAccount("1234568");
		corporateCredit.setTxnCode(1);
		return Arrays.asList(bankDebit,bankCredit,corporateDebit,corporateCredit);
		
	}

}
