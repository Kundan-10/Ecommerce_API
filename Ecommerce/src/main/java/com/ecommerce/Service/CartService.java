package com.ecommerce.Service;

import java.util.List;

import com.ecommerce.Exception.CartException;
import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.OrderListException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;

public interface CartService {
	
	public List<Cart> allProduct(String key)throws CartException, CustomerException ;
	
	public Product AddProductCart(String key,Integer productId)throws ProductException,CustomerException;
	public Integer totalAmount(String key)throws CartException,CustomerException;
	public Product deleteProductToCart(String key,Integer productId)throws CartException,CustomerException;
	public Product increaseProductQuantity(String key,Integer productId, Integer increaseValue)throws CartException,CustomerException;
	public List<Product> buyNow(String Key)throws CustomerException, CartException, ProductException, OrderListException ;
}
