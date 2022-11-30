package com.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Exception.CartException;
import com.ecommerce.Exception.CustomerException;
import com.ecommerce.Exception.OrderListException;
import com.ecommerce.Exception.ProductException;
import com.ecommerce.Service.CartService;
import com.ecommerce.Service.OrderListService;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;

@RestController

public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderListService orderListService;
	
	@PostMapping("/productAddInCart")
	public ResponseEntity<Product>  addProductToCart(@RequestParam("Key") String key, @RequestParam("productId") Integer productId) throws ProductException, CustomerException{
		Product pro=cartService.AddProductCart(key,productId);
		return new ResponseEntity<Product>(pro,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/deleteProduct")
	public ResponseEntity<Product>  deleteCartProduct(@RequestParam("Key") String key, @RequestParam("productId") Integer productId) throws ProductException, CartException, CustomerException{
		Product pro=cartService.deleteProductToCart(key,productId);
		return new ResponseEntity<Product>(pro,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/cart/{ProductId}")
	public ResponseEntity<Product> increaseProduct(@RequestParam("key") String key, @PathVariable("id") Integer id,@RequestParam("NumberOfIncrement") Integer inc) throws CartException, CustomerException{
		Product pro = cartService.increaseProductQuantity(key, id, inc);
		return new ResponseEntity<Product>(pro,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/cart")
	public ResponseEntity<Integer> totalAmountOfCart(@RequestParam("Key") String key) throws CartException, CustomerException{
		Integer total = cartService.totalAmount(key);
		return new ResponseEntity<Integer>(total,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/buyNow")
	public ResponseEntity<String> buyNow(@RequestParam String key) throws CustomerException, CartException, ProductException, OrderListException{
		List<Product> prodoct= cartService.buyNow(key);
		orderListService.addCartToOrderList(prodoct);
		return new ResponseEntity<String>("Prdouct Buy Successfullyy." , HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/allProduct")
	public ResponseEntity<List<Cart>> listOfCart(@RequestParam String key) throws CartException, CustomerException{
		List<Cart> listOfCarts = cartService.allProduct(key);
		return new ResponseEntity<List<Cart>>(listOfCarts,HttpStatus.ACCEPTED);
	}
}
