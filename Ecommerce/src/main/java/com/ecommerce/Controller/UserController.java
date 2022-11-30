package com.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.Service.ProductService;
import com.ecommerce.Service.UserService;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.UserDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody UserDto userdto){
		User user= new User();
		user.setUserId(userdto.getUserId());
		user.setUserName(userdto.getUserName());
		user.setUserEmail(userdto.getUserEmail());
		user.setPassword(userdto.getPassword());
		
	
		
		User userData = userService.addUser(user);
		return new ResponseEntity<User>(userData,HttpStatus.ACCEPTED);
	}
	
	
	
	
	@GetMapping("/viewProductByUser")
	public ResponseEntity<List<Product>> viewAllProduct(@RequestParam String key) throws ProductException, CustomerException{
		List<Product> listOfProducts = productService.viewProductByUser(key);
		return new ResponseEntity<List<Product>>(listOfProducts,HttpStatus.CREATED);
	}
	
	
	
	
	@GetMapping("/viewProductType/{type}")
	public ResponseEntity<List<Product>> viewAllProductByType(@RequestParam String key,@PathVariable("type") String productType) throws ProductException, CustomerException{
		List<Product> listOfProducts = productService.viewProductByType(key, productType);
		return new ResponseEntity<List<Product>>(listOfProducts,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/viewProductname/{name}")
	public ResponseEntity<List<Product>> viewAllProductByName(@RequestParam String key,@PathVariable("name") String productName) throws ProductException, CustomerException{
		List<Product> listOfProducts = productService.viewProductByType(key, productName);
		return new ResponseEntity<List<Product>>(listOfProducts,HttpStatus.CREATED);
	}
	
	
	
	
	
	@GetMapping("/filterByTwoPrice/{price1}/{price2}")
	public ResponseEntity<List<Product>> viewAllProductByTwoPrice(@RequestParam String key,@PathVariable("price1") Integer price1,@PathVariable("price2") Integer price2) throws ProductException, CustomerException{
		List<Product> listOfProducts = productService.viewProductByBetweenToPrice(key, price1, price2);
		return new ResponseEntity<List<Product>>(listOfProducts,HttpStatus.CREATED);
	}
	
	

}
