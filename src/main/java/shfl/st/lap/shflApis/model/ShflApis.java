package shfl.st.lap.shflApis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_API")
public class ShflApis extends Auditable {

	@Id
	@Column(name = "api_code")
	private String apiCode;

	@Column(name = "api_url")
	private String apiUrl;

}
