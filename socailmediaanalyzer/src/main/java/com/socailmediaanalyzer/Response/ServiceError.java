package com.socailmediaanalyzer.Response;

public class ServiceError {
	
	private ErrorSeverity severity;	
	private String message;
	private String code;
	
	public ServiceError() {
		super();
	}
	
	public ServiceError(ErrorSeverity severity, String message) {
		super();
		this.severity = severity;
		this.message = message;
	}

	public ServiceError(ErrorSeverity severity, String code, String message) {
		this.severity = severity;
		this.code = code;
		this.message = message;
	}
	
	public ErrorSeverity getSeverity() {
		return severity;
	}
	public void setSeverity(ErrorSeverity severity) {
		this.severity = severity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
