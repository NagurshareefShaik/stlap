package shfl.st.lap.nach.enums;

public enum StatusEnum {

	NEW("NEW"), REGISTERED("REGISTERED"), VERIFIED("VERIFIED"), APPROVED("APPROVED"),
	NACH_VERIFICATION_APPROVED("NACH_VERIFICATION_APPROVED"), NACH_VERIFICATION_REJECTED("NACH_VERIFICATION_REJECTED"),
	NACH_APPROVED("NACH_APPROVED"), NACH_REJECTED("NACH_REJECTED");

	String value;

	StatusEnum(String value) {
		this.value = value;
	}

}
