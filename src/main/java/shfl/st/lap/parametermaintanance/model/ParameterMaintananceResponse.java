package shfl.st.lap.parametermaintanance.model;

import lombok.Data;

@Data
public class ParameterMaintananceResponse{

	private int paramId;

	private String paramName;

	private String paramValue;
	
	private String module;

	private String paramDataType;

	private String paramEffStartDate;

	private String paramEffEndDate;

	private String branch;

}
