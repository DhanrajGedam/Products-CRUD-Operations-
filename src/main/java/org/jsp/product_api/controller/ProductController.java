package org.jsp.product_api.controller;

import java.util.List;

import org.jsp.product_api.dto.Product;
import org.jsp.product_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;

	@PostMapping("/products")
	public ResponseEntity<Object> saveProduct(@RequestBody Product product){
		return service.saveProduct(product);
	}
	
	@PostMapping("/products/many")
	public ResponseEntity<Object> saveProducts(@RequestBody List<Product> products){
		return service.saveProducts(products);
	}
	
	@GetMapping("/products")
	public ResponseEntity<Object> fetchAllProduct(){
		return service.fetchAllProduct();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> fetchById(@PathVariable int id){
		return service.fetchById(id);
	}
	
	@GetMapping("/products/name/{name}")
	public ResponseEntity<Object> fetchByName(@PathVariable("name") String product_name){
	return service.fetchByName(product_name);
	}
	
	@GetMapping("/products/price/{price}")
	public ResponseEntity<Object> fetchByPriceGreater(@PathVariable("price") double product_price){
		return service.fetchByPriceGreater(product_price);
	}
	
	@GetMapping("/products/stock/{min}/{max}")
	public ResponseEntity<Object> fetchByStockBetween(@PathVariable("min") int min_price,
													  @PathVariable("max") int max_price){
		return service.fetchByStockBetween(min_price,max_price);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public ResponseEntity<Object> deletebyId(@PathVariable("id") int product_id){
		return service.deletebyId(product_id);
	}
	
	@PutMapping("/products/update")
	public ResponseEntity<Object> updateRecord(@RequestBody Product product){
		return service.updateProduct(product);
	}
	
	@PatchMapping("/products/update/{id}")
	public ResponseEntity<Object> updateRecord (@PathVariable("id") int product_id,
												@RequestBody Product product){
		return service.updateRecord(product_id,product);
	}
}
