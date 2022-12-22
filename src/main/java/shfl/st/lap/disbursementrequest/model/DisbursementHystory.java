package shfl.st.lap.disbursementrequest.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_DISB_HYSTORY")
public class DisbursementHystory {
	
	@Id
	@Column(name = "tanscation_id")
	private int tanscationId;
	
	//second key
	//auto generate
	@Column(name = "disb_hystory_id")
	private int disbHystoryId;
	
	@Column(name = "application_number")
	private String applicationNumber;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	

}
