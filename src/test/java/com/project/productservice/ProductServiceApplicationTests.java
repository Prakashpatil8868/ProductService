package com.project.productservice;

import com.project.productservice.models.Product;
import com.project.productservice.projections.ProductWithIdTitlePrice;
import com.project.productservice.repositories.ProductRepository;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	ProductRepository productRepository;
@Autowired
	public ProductServiceApplicationTests(ProductRepository productRepository){
		this.productRepository=productRepository;
	}

	@Test
	void contextLoads() {
	}
	@Test
	void getData(){

	//Optional<Product>  productOptional=productRepository.getByTitleContaining("phone");
//Product product=productOptional.get();
//		System.out.println(product.getPrice());
		List<Product> products=productRepository.something(1L);
		for (Product p:products){
			System.out.println(p.getId()+" "+p.getTitle());
		}
}

@Test
	void getSpecificData(){
	List<ProductWithIdTitlePrice> products=productRepository.somethingSpecific(1L);
	for (ProductWithIdTitlePrice p:products){
		System.out.println(p.getId()+" "+p.getTitle());
	}
}

}
