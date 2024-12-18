package org.jsp.product_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsp.product_api.dto.Product;
import org.jsp.product_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;

	public ResponseEntity<Object> saveProduct(Product product) {
		repository.save(product);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Product Added Success");
		map.put("data", product);
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> saveProducts(List<Product> products) {
		repository.saveAll(products);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Product Added Success");
		map.put("data", products);

		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> fetchAllProduct() {
		List<Product> list = repository.findAll();
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found");

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}

	}
	
	public ResponseEntity<Object> fetchById(int id) {
		Optional<Product> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found with Id: "+id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", optional.get());

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByName(String product_name) {
		List<Product> list = repository.findByName(product_name);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found with Name :"+product_name);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByPriceGreater(double product_price) {
		List<Product> list = repository.findByPriceGreaterThanEqual(product_price);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found Price Greater Than: "+product_price);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByStockBetween(int min_price, int max_price) {
		List<Product> list = repository.findByStockBetween(min_price,max_price);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found Stock Between: "+min_price+" and "+max_price);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}

	}

	public ResponseEntity<Object> deletebyId(int product_id) {
		Optional<Product> optional = repository.findById(product_id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Product Found with Id: "+product_id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			repository.deleteById(product_id);
			map.put("message", "Product Deleted Success");

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> updateProduct(Product product) {
		repository.save(product);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Product Updated Success");

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateRecord(int product_id, Product product) {
		Optional<Product> optional = repository.findById(product_id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Product Found with Id: "+product_id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Product existingProduct = optional.get();
			if(product.getName()!=null)
				existingProduct.setName(product.getName());
			if(product.getDescription()!=null)
				existingProduct.setDescription(product.getDescription());
			if(product.getPrice()!=0)
				existingProduct.setPrice(product.getPrice());
			if(product.getStock()!=0)
				existingProduct.setStock(product.getStock());
			
			repository.save(existingProduct);
			
			
			map.put("message", "Product Updated Success");

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

		
	
}
