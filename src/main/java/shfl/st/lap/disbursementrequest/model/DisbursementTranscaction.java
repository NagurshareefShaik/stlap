package shfl.st.lap.disbursementrequest.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_TRANS")
public class DisbursementTranscaction {
	
	@Id
	@Column(name = "transaction_id")
	private int transactionId;
	
	//second key
	@Column(name = "program_id")
	private int programId;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	

}
