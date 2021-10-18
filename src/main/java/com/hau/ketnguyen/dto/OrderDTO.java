package com.hau.ketnguyen.dto;

public class OrderDTO extends BaseDTO<OrderDTO>{
	private boolean status;
	private float amount;
	private String email;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
