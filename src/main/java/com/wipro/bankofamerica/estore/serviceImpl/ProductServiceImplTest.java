package com.wipro.bankofamerica.estore.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.repository.ProductRepository;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	ProductRepository productRepository;

	@Test
	public void saveProductTest() {
		Product product = new Product(3, 1255, "camera", "this is camera", "2", "5000", "active");
		when(productRepository.save(product)).thenReturn(product);
		assertEquals(product, productService.saveProduct(product));
	}

	@Test
	public void getAllProductTest() {
		List<Product> list = new ArrayList<>();
		Product product1 = new Product(3, 1256, "laptop", "this is laptop", "1", "50000", "active");
		Product product2 = new Product(4, 1257, "printer", "this is printer", "1", "15000", "active");
		Product product3 = new Product(5, 1258, "mouse", "this is mouse", "2", "1800", "active");

		list.add(product1);
		list.add(product2);
		list.add(product3);

		when(productRepository.findAll()).thenReturn(list);

		Iterable<Product> productList = productService.getAllProduct();
		assertEquals(list, productList);

	}

	@Test
	public void getProductByIdTest() {
		when(productRepository.findById(3))
				.thenReturn(new Product(3, 103, "laptop", "this is laptop", "2", "150000", "active"));
		Product product = productService.getProductById(3);
		assertEquals("103", product.getProductId());
		assertEquals("laptop", product.getProductName());
		assertEquals("active", product.getStatus());

	}

	@Test
	public void deleteProductById() {
		Product product = new Product(1, 101, "laptop", "this is laptop", "1", "55000", "active");
		productRepository.save(product);
		productRepository.deleteById(product.getId());
		Product p = productRepository.findById(product.getId());
		assertEquals(null, p);
	}

}
