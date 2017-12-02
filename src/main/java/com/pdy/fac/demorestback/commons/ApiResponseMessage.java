package com.pdy.fac.demorestback.commons;

import javax.xml.bind.annotation.XmlTransient;

@javax.xml.bind.annotation.XmlRootElement
public class ApiResponseMessage {
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	public static final int OK = 4;
	public static final int TOO_BUSY = 5;

	int code;
	String type;
	String message;

	public ApiResponseMessage() {
	}

	public ApiResponseMessage(final int code, final String message) {
		this.code = code;
		switch (code) {
		case ERROR:
			setType("error");
			break;
		case WARNING:
			setType("warning");
			break;
		case INFO:
			setType("info");
			break;
		case OK:
			setType("ok");
			break;
		case TOO_BUSY:
			setType("too busy");
			break;
		default:
			setType("unknown");
			break;
		}
		this.message = message;
	}

	@XmlTransient
	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
