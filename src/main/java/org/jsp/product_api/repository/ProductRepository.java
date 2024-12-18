package org.jsp.product_api.repository;

import java.util.List;

import org.jsp.product_api.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByName(String product_name);

	List<Product> findByPriceGreaterThanEqual(double product_price);

	List<Product> findByStockBetween(int min_price, int max_price);
}
