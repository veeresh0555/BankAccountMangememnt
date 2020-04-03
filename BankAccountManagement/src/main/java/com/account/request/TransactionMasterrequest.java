package com.account.request;

public class TransactionMasterrequest {

	private long transid;
	
	private long frmaccount;
	
	private long toaccount;
	
	private Double amount;
	
	private String remarks;

	 public long getTransid() {
		return transid;
	}

	public void setTransid(long transid) {
		this.transid = transid;
	}

	public long getFrmaccount() {
		return frmaccount;
	}

	public void setFrmaccount(long frmaccount) {
		this.frmaccount = frmaccount;
	}

	public long getToaccount() {
		return toaccount;
	}

	public void setToaccount(long toaccount) {
		this.toaccount = toaccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTransdate() {
		return transdate;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	private String transdate;
	
	 
	
}
