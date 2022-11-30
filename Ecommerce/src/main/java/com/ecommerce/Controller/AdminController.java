package com.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Service.AdminService;
import com.ecommerce.Service.ProductService;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		Admin ad= adminService.addAdmin(admin);
		return new ResponseEntity<Admin>(ad,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestParam String key, @RequestBody Product product) throws CustomerException{
		Product pr= productService.addProduct(key, product);
		return new ResponseEntity<Product>(pr,HttpStatus.ACCEPTED);
	}
	
	
	
	
	@GetMapping("/viewProductByAdmin")
	public ResponseEntity<List<Product>> viewAllProduct(@RequestParam String key) throws ProductException, CustomerException{
		List<Product> listOfProducts = productService.viewProductByAdmin(key);
		return new ResponseEntity<List<Product>>(listOfProducts,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/viewProductt/{id}")
	public ResponseEntity<Product> viewAllProductById(@RequestParam String key,  @PathVariable("id") Integer id) throws ProductException, CustomerException{
		Product product = productService.viewProductById(key,id);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	

}
