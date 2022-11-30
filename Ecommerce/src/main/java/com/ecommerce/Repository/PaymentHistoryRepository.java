package com.ecommerce.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.PaymentHistory;


@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer>{

	public List<PaymentHistory>  findByPaymentType(String paymentType);
	public List<PaymentHistory> findByDate(LocalDate transactionDate);	
	public List<PaymentHistory> findByDateBetween(LocalDate from, LocalDate to);
	
}
