package com.ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.Repository.CurrentAdminSessionRepository;
import com.ecommerce.Repository.CurrentUserSessionRepository;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.model.CurrentAdminSession;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CurrentUserSessionRepository currentUserSessionRepository;
	
	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepository;
	
	

	@Override
	public Product addProduct(String key, Product product) throws CustomerException {
		CurrentAdminSession currAdmin =currentAdminSessionRepository.findByUuid(key);
		if(currAdmin==null) throw new CustomerException("Please Login first");
		
	    return productRepository.save(product);	
	}
	
	
	
	
	

	@Override
	public List<Product> viewProductByAdmin(String key) throws ProductException, CustomerException {
		CurrentAdminSession currAdmin =currentAdminSessionRepository.findByUuid(key);
		if(currAdmin==null) throw new CustomerException("Please Login first");
		
		List<Product>  listOfProducts = productRepository.findAll();
		if(listOfProducts.size()==0)throw new ProductException("No any product. please Add.");
		return listOfProducts;
	}






	@Override
	public Product viewProductById(String key, Integer productId) throws ProductException, CustomerException {
		CurrentAdminSession currAdmin =currentAdminSessionRepository.findByUuid(key);
		if(currAdmin==null) throw new CustomerException("Please Login first");
		
		Optional<Product> pro =productRepository.findById(productId);
		if(!pro.isPresent())throw new ProductException("Product Id invalid.");
		return pro.get();
	}






	@Override
	public List<Product> viewProductByUser(String key) throws ProductException, CustomerException {
		CurrentUserSession currUser =currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<Product>  listOfProducts = productRepository.findAll();
		if(listOfProducts.size()==0)throw new ProductException("No any product. please Add.");
		return listOfProducts;
	}






	@Override
	public List<Product> viewProductByType(String key, String productType) throws ProductException, CustomerException {
		CurrentUserSession currUser =currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<Product>  listOfProducts = productRepository.findByProductType(productType);
		if(listOfProducts.size()==0)throw new ProductException("No any product. please Add.");
		return listOfProducts;
	}






	@Override
	public List<Product> viewProductByName(String key, String productName) throws ProductException, CustomerException {
		CurrentUserSession currUser =currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		List<Product>  listOfProducts = productRepository.findByProductName(productName);
		if(listOfProducts.size()==0)throw new ProductException("No any product. please Add.");
		return listOfProducts;
	}






	@Override
	public List<Product> viewProductByBetweenToPrice(String key, Integer price1, Integer price2)throws ProductException, CustomerException {
		CurrentUserSession currUser =currentUserSessionRepository.findByUuid(key);
		if(currUser==null) throw new CustomerException("Please Login first");
		
		
		List<Product>  listOfProducts = productRepository.findByPriceBetween(price1, price2);
		if(listOfProducts.size()==0)throw new ProductException("No any product. please Add.");
		return listOfProducts;
	}
	
}
