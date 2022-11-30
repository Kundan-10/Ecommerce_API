package com.ecommerce.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.PaymentHistoryException;
import com.ecommerce.Service.PaymentHistoryService;
import com.ecommerce.model.PaymentHistory;

@RestController
public class PaymentHistoryController {
	
	@Autowired
	private PaymentHistoryService paymentHistoryService;
	
	@GetMapping("historybytwodate")
	public ResponseEntity<List<PaymentHistory>> viewByTwoDate(@RequestParam String key, @RequestParam("one") String one, @RequestParam("two")  String two) throws PaymentHistoryException, CustomerException {
		LocalDate firstDate= LocalDate.parse(one);
		LocalDate secondDate = LocalDate.parse(two);
		List<PaymentHistory> listOTransactions = paymentHistoryService.viewPaymentHistoryByDate(key,firstDate, secondDate);
		return new ResponseEntity<List<PaymentHistory>>(listOTransactions,HttpStatus.ACCEPTED);
	}
	
	
	
	
	@GetMapping("findbydate")
	public ResponseEntity<List<PaymentHistory>> viewByDate(@RequestParam String key,  @RequestParam("two")  String two) throws PaymentHistoryException, CustomerException {
		LocalDate secondDate = LocalDate.parse(two);
		List<PaymentHistory> listOTransactions = paymentHistoryService.findByDate(key, secondDate);
		return new ResponseEntity<List<PaymentHistory>>(listOTransactions,HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("findbytype")
	public ResponseEntity<List<PaymentHistory>> findByType(@RequestParam String key,  @RequestParam("two")  String type) throws PaymentHistoryException, CustomerException {
		List<PaymentHistory> listOTransactions = paymentHistoryService.findByPaymentHistoryType(key, type);
		return new ResponseEntity<List<PaymentHistory>>(listOTransactions,HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("paymenthistory/{id}")
	public ResponseEntity<PaymentHistory> findById(@RequestParam String key,  @PathVariable("id") Integer id) throws PaymentHistoryException, CustomerException {
        PaymentHistory history = paymentHistoryService.findByPaymentHistoryId(key, id);
		return new ResponseEntity<PaymentHistory>(history,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("paymenthistory")
	public ResponseEntity<List<PaymentHistory>> viewAll(@RequestParam String key) throws PaymentHistoryException, CustomerException {
		List<PaymentHistory> listOTransactions = paymentHistoryService.viewAllPaymentHistory(key);
		return new ResponseEntity<List<PaymentHistory>>(listOTransactions,HttpStatus.ACCEPTED);
	}
	
	
		

}
