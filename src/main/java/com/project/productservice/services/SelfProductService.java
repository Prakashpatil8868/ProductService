package com.project.productservice.services;

import com.project.productservice.DTO.RequestDTO;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.repositories.CategoryRepository;
import com.project.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("selfProductService")
public class SelfProductService implements IProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundExceptions {
      Optional<Product> productOptional =productRepository.findById(id);
      if (productOptional.isEmpty()){
          throw new ProductNotFoundExceptions("product with"+id+" does not exist");
      }
      Product product=productOptional.get();
      return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
       Optional<Category> categoryOptional=categoryRepository.
              findByName(product.getCategory().getName());
        //Category savedCategory;

       if(categoryOptional.isEmpty()){
//Category category=new Category();
//category.setName(product.getCategory().getName());
 //savedCategory=categoryRepository.save(category);
           //Due to cascade product automatically create category
       }else {
product.setCategory(categoryOptional.get());
       }

       Product savedProduct=productRepository.save(product);
       return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundExceptions {
        Optional<Product> productOptional=productRepository.findById(id);
if(productOptional.isEmpty()){
    throw new ProductNotFoundExceptions("product with "+id+" not found in table.not able to update");
}
Product existingProduct=productOptional.get();
Product productToBeSaved=new Product();

productToBeSaved.setTitle(
        product.getTitle()!=null?
                product.getTitle() :
                existingProduct.getTitle()
);
        productToBeSaved.setDescription(
                product.getDescription()!=null?
                        product.getDescription() :
                        existingProduct.getDescription()
        );
        productToBeSaved.setImageUrl(
                product.getImageUrl()!=null?
                        product.getImageUrl() :
                        existingProduct.getImageUrl()
        );

        if(product.getCategory().getName()!=null){
            Optional<Category> categoryOptional=categoryRepository.
                    findByName(product.getCategory().getName());
            Category savedCategory;

            if(categoryOptional.isEmpty()){
                Category category=new Category();
                category.setName(product.getCategory().getName());
                savedCategory=categoryRepository.save(category);
            }else {
                savedCategory = categoryOptional.get();
            }
            productToBeSaved.setCategory(savedCategory);

        }else {
            productToBeSaved.setCategory(existingProduct.getCategory());
        }
productToBeSaved.setId(id);

        return productRepository.save(productToBeSaved);
    }

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO) {
        return null;
    }
}
