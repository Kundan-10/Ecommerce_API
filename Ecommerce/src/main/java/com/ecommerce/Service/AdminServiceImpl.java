package com.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repository.AdminRepository;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

}
