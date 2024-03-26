package com.project.productservice.repositories;

import com.project.productservice.models.Product;
import com.project.productservice.projections.ProductWithIdTitlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
Optional<Product> getByTitleContaining(String word);
//void deleteBy(Long id);
Optional<Product> findById(Long id);
Product save(Product product);

@Query("select p from Product p where p.id= :id")
List<Product> something(@Param("id") Long id);

    @Query("select p.id as id,p.title as title,p.price as price from Product p where p.id= :id")
    List<ProductWithIdTitlePrice> somethingSpecific(@Param("id") Long id);

}
