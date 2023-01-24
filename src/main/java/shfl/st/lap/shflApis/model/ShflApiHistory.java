package shfl.st.lap.shflApis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
@Entity
@Table(name = "ST_TB_LMS_SHFL_API_H")
public class ShflApiHistory extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "api_hist_id")
	private int apiHistoryId;

	@Column(name = "api_code")
	private String apiCode;

	@Column(name = "api_url")
	private String apiUrl;

	@Column(name = "login_user")
	private String loginUser;

}
