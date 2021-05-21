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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + authorID;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimID;
		result = prime * result + ((resolveDate == null) ? 0 : resolveDate.hashCode());
		result = prime * result + resolverID;
		result = prime * result + statusID;
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		result = prime * result + typeID;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursment other = (Reimbursment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (authorID != other.authorID)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimID != other.reimID)
			return false;
		if (resolveDate == null) {
			if (other.resolveDate != null)
				return false;
		} else if (!resolveDate.equals(other.resolveDate))
			return false;
		if (resolverID != other.resolverID)
			return false;
		if (statusID != other.statusID)
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		if (typeID != other.typeID)
			return false;
		return true;
	}
	
	
}
