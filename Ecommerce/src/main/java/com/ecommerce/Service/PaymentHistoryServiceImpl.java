package com.ecommerce.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.PaymentHistoryException;
import com.ecommerce.Repository.CurrentUserSessionRepository;
import com.ecommerce.Repository.PaymentHistoryRepository;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.PaymentHistory;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService{

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;
	
	@Autowired
	private CurrentUserSessionRepository currentSessionDao;

	
	
	
	
	
	@Override
	public List<PaymentHistory> viewPaymentHistoryByDate(String key, LocalDate from, LocalDate to)throws PaymentHistoryException, CustomerException {
		CurrentUserSession currUser=currentSessionDao.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
			
		List<PaymentHistory> listOfPaymentHistories = paymentHistoryRepository.findByDateBetween(from, to);
		if(listOfPaymentHistories.size()==0)throw new PaymentHistoryException("List Is Empy..");
		return listOfPaymentHistories;
	}

	
	
	
	
	@Override
	public List<PaymentHistory> findByPaymentHistoryType(String key, String transactionType)
			throws PaymentHistoryException, CustomerException {
		CurrentUserSession currUser=currentSessionDao.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<PaymentHistory> listOfPaymentHistories = paymentHistoryRepository.findByPaymentType(transactionType);
		if(listOfPaymentHistories.size()==0)throw new PaymentHistoryException("List Is Empy..");
		return listOfPaymentHistories;
	}

	
	
	
	
	@Override
	public PaymentHistory findByPaymentHistoryId(String key, Integer id)
			throws PaymentHistoryException, CustomerException {
		CurrentUserSession currUser=currentSessionDao.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		Optional<PaymentHistory> history= paymentHistoryRepository.findById(id);
		if(!history.isPresent())throw new PaymentHistoryException("This Id doe't exsit..");
		return history.get();
	}
	
	
	
	
	
	

	@Override
	public List<PaymentHistory> viewAllPaymentHistory(String key) throws PaymentHistoryException,CustomerException {
		CurrentUserSession currUser=currentSessionDao.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<PaymentHistory> listOfPaymentHistories = paymentHistoryRepository.findAll();
		if(listOfPaymentHistories.size()==0)throw new PaymentHistoryException("List Is Empy..");
		return listOfPaymentHistories;

	}
	
	
	
	

	@Override
	public List<PaymentHistory> findByDate(String key, LocalDate date)throws PaymentHistoryException, CustomerException {
		CurrentUserSession currUser=currentSessionDao.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
			
		List<PaymentHistory> listOfPaymentHistories = paymentHistoryRepository.findByDate(date);
		if(listOfPaymentHistories.size()==0)throw new PaymentHistoryException("List Is Empy..");
		return listOfPaymentHistories;
	}
	
	
	
	
	

	@Override
	public PaymentHistory addPaymentHistory(PaymentHistory paymentHistory) throws PaymentHistoryException {
		if(paymentHistory==null)throw new PaymentHistoryException("Null is not allow..");
		return paymentHistoryRepository.save(paymentHistory);
	}
	
	

	
}
