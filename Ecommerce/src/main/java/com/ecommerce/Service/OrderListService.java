package com.ecommerce.Service;

import java.util.List;

import com.ecommerce.Exception.CartException;
import com.ecommerce.Exception.OrderListException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.model.Product;

public interface OrderListService {
	
	public List<Product> addCartToOrderList(List<Product> products)throws ProductException,CartException,OrderListException;

}
