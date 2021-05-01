package com.shop.repository;

import java.util.List;

import com.shop.model.Payment;
import com.shop.model.User;

public interface PaymentDAO {
	
	public boolean createPayment(Payment p);
	
	public boolean payWeeklyPayment();
	
	public List<Payment> viewRemainPayments(User u);
	
	public List<Payment> viewAllPayments(User u);
}
