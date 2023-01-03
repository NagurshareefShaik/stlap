package shfl.st.lap.statusmaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ST_TB_LMS_STATUS_MASTER")
public class Status {
	
	@Id
	@GeneratedValue
	@Column(name = "status_id")
	private int statusId;
	
	@Column(name = "status_name")
	private String statusName;

}
