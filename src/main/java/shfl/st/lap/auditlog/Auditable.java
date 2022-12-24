package shfl.st.lap.auditlog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	@CreatedBy
	@Column(name = "created_by", nullable = false, updatable = false)
	private String createdBy;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private Date createdDate;

	@LastModifiedBy
	private String lastModifiedBy;

	@Temporal(value = TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModifiedDate;

}
