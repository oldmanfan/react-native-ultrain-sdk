package com.ultrain.sdk.deps.data.remote.model.api;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * 
 * @author espritblock http://eblock.io
 *
 */
public class Block {

	@Expose
	private String timestamp;

	@Expose
	private String producer;

	@Expose
	private Long confirmed;

	@Expose
	private String previous;

	@Expose
	private String transactionMroot;

	@Expose
	private String actionMroot;

	@Expose
	private String scheduleVersion;

	@Expose
	private String id;

	@Expose
	private Long blockNum;

	@Expose
	private Long refBlockPrefix;

	public Block() {

	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Long getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Long confirmed) {
		this.confirmed = confirmed;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getTransactionMroot() {
		return transactionMroot;
	}

	public void setTransactionMroot(String transactionMroot) {
		this.transactionMroot = transactionMroot;
	}

	public String getActionMroot() {
		return actionMroot;
	}

	public void setActionMroot(String actionMroot) {
		this.actionMroot = actionMroot;
	}

	public String getScheduleVersion() {
		return scheduleVersion;
	}

	public void setScheduleVersion(String scheduleVersion) {
		this.scheduleVersion = scheduleVersion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBlockNum() {
		return blockNum;
	}

	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}

	public Long getRefBlockPrefix() {
		return refBlockPrefix;
	}

	public void setRefBlockPrefix(Long refBlockPrefix) {
		this.refBlockPrefix = refBlockPrefix;
	}
}
