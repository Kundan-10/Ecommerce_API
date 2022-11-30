package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public List<Product> findByProductType(String productType);
	
	public List<Product> findByProductName(String productName);
	
	public List<Product> findByPriceBetween(Integer price1,Integer price2);

}
