package com.ultrain.sdk.deps.rpc.exception;

import com.google.gson.annotations.Expose;

/**
 *
 * @author espritblock http://eblock.io
 *
 */
public class ApiError {

	@Expose
	private String message;

	@Expose
	private String code;

	@Expose
	private Error error;

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

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
