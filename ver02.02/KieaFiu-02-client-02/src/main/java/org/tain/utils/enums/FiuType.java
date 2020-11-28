package org.tain.utils.enums;

public enum FiuType {
	BIZ_OPEN("Biz Open"),
		FILE_START("File Start"),
			FILE_SEND_DATA("File Send Data"),
			FILE_CHECK("File Check"),
		FILE_FINISH("File Finish"),
	BIZ_CLOSE("Biz Close"),
	FIU_END("FIU End"),
	ERROR_EXCEPTION("Error Exception");

	private String value;

	FiuType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
