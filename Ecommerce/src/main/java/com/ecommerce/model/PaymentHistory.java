package com.ecommerce.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class PaymentHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	private String paymentType;
	private Integer balence;
	private LocalDate date;
	private String discription;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;

}
