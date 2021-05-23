package com.ers.models;

public class Reimbursment {
	private int reimID;
	private double amount;
	private String submitDate;
	private String resolveDate;
	private String description;
	private String receipt;
	private int authorID;
	private int resolverID;
	private int statusID;
	private int typeID;
	
	public Reimbursment() {
		
	}
	
	public Reimbursment(double amount, String description, int authorID, int statusID,
			int typeID) {
		super();
		this.amount = amount;
		this.description = description;
		this.authorID = authorID;
		this.statusID = statusID;
		this.typeID = typeID;
	}
	
	public Reimbursment(double amount, String description, int authorID, int resolverID, int statusID,
			int typeID) {
		super();
		this.amount = amount;
		this.description = description;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}

	public Reimbursment(int reimID, double amount, String submitDate, String resolveDate, String description,
			String receipt, int authorID, int resolverID, int statusID, int typeID) {
		super();
		this.reimID = reimID;
		this.amount = amount;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.description = description;
		this.receipt = receipt;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}
	
	public int getReimID() {
		return reimID;
	}
	public void setReimID(int reimID) {
		this.reimID = reimID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getResolveDate() {
		return resolveDate;
	}
	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getResolverID() {
		return resolverID;
	}
	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	@Override
	public String toString() {
		return "Reimbursment [reimID=" + reimID + ", amount=" + amount + ", submitDate=" + submitDate + ", resolveDate="
				+ resolveDate + ", description=" + description + ", receipt=" + receipt + ", authorID=" + authorID
				+ ", resolverID=" + resolverID + ", statusID=" + statusID + ", typeID=" + typeID + "]";
	}

}
