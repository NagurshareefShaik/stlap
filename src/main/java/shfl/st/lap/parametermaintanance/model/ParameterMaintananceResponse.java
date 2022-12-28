package shfl.st.lap.parametermaintanance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import shfl.st.lap.auditlog.Auditable;

@Data
public class ParameterMaintananceResponse{

	private int paramId;

	private String paramName;

	private String paramValue;

	private String paramDataType;

	private String paramEffStartDt;

	private String paramEffEndDt;

}
