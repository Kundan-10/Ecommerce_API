package com.ecommerce.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Exception.CartException;
import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.OrderListException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.Repository.CartRepository;
import com.ecommerce.Repository.CurrentUserSessionRepository;
import com.ecommerce.Repository.OrderListRepository;
import com.ecommerce.Repository.PaymentHistoryRepository;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.PaymentHistory;
import com.ecommerce.model.Product;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderListRepository orderListService;
	
	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;
	
	@Autowired
	private CurrentUserSessionRepository currentUserSessionRepository;

	@Override
	public Product AddProductCart(String key,Integer productId) throws ProductException,CustomerException {
		
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		
		Optional<Product> pro = productRepository.findById(productId);
		if(!pro.isPresent())throw new ProductException("Product Id Invalid..");
		
		Cart cart = new Cart();
		cart.setCartId(pro.get().getProductId());
		cart.setProduct(pro.get());
		cartRepository.save(cart);
		return pro.get();
	}

	
	
	@Override
	public Integer totalAmount(String key) throws CartException,CustomerException {
		
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		
		List<Cart> listOfCarts = cartRepository.findAll();
		if(listOfCarts.size()==0)throw new CartException("Cart is empty.");
		
		Integer amount=0;
		for(Cart cart:listOfCarts)amount+=cart.getProduct().getPrice();
		return amount;
	}



	@Override
	public Product deleteProductToCart(String key,Integer productId) throws CartException,CustomerException {
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		
	Optional<Cart> cart=	cartRepository.findById(productId);
	if(!cart.isPresent())throw new CartException("Data Not Found..");
		cartRepository.deleteById(productId);
		return cart.get().getProduct();
	}



	@Override
	public Product increaseProductQuantity(String key,Integer productId, Integer increaseValue) throws CartException,CustomerException {
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		Optional<Cart> car=	cartRepository.findById(productId);
		if(!car.isPresent())throw new CartException("Data Not Found..");
		
		for(int i=0;i<increaseValue;i++) {
		Cart cart = new Cart();
		cart.setCartId(car.get().getProduct().getProductId()+i);
		cart.setProduct(car.get().getProduct());
		cartRepository.save(cart);
		}
		return car.get().getProduct();
	}



	@Override
	public List<Product> buyNow(String key) throws CustomerException, CartException, ProductException, OrderListException {
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<Cart> listOfCarts = cartRepository.findAll();
		if(listOfCarts.size()==0)throw new CartException("Cart is Empty.");
		List<Product> products = new ArrayList<>();
		
		for(Cart cart:listOfCarts) {
			PaymentHistory payment = new PaymentHistory();
			payment.setBalence(cart.getProduct().getPrice());
			payment.setDate(LocalDate.now());
			payment.setPaymentType(cart.getProduct().getProductType());
			payment.setDiscription("Payment added Successfully..");
			
			paymentHistoryRepository.save(payment);
			cartRepository.deleteAll();
			products.add(cart.getProduct());
		}
//		cartRepository.deleteAll();
		
		return products;
	}



	@Override
	public List<Cart> allProduct(String key) throws CartException, CustomerException {
		CurrentUserSession currUser=currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<Cart> listOfProducts = cartRepository.findAll();
		return listOfProducts; 
	}
	

}
