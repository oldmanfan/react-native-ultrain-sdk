package com.ultrain.sdk.deps.rpc.exception;

import com.google.gson.annotations.Expose;

/**
 *
 * @author espritblock http://eblock.io
 *
 */
public class ErrorDetails {

	@Expose
	private String message;

	@Expose
	private String file;

	@Expose
	private Integer lineNumber;

	@Expose
	private String method;

	private ErrorDetails() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
