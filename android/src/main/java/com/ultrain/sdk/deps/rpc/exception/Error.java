package com.ultrain.sdk.deps.rpc.exception;

import com.google.gson.annotations.Expose;

/**
 *
 * @author espritblock http://eblock.io
 *
 */
public class Error {

	@Expose
	private String code;

	@Expose
	private String name;

	@Expose
	private String what;

	@Expose
	private ErrorDetails[] details;

	private Error() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public ErrorDetails[] getDetails() {
		return details;
	}

	public void setDetails(ErrorDetails[] details) {
		this.details = details;
	}

}
