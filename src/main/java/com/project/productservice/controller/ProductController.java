package com.project.productservice.controller;

import com.project.productservice.DTO.RequestDTO;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") IProductService productService){
        this.productService=productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundExceptions {
ResponseEntity responseEntity;
       Product product=productService.getSingleProduct(id);
        responseEntity=new ResponseEntity<>(
               product,
               HttpStatus.OK
       );
    return responseEntity;
    }



    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories(){

        ResponseEntity responseEntity=new ResponseEntity<>(
                new ArrayList<>(),
                HttpStatus.ACCEPTED
        );
        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity responseEntity=new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
        return  responseEntity;
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody RequestDTO requestDTO){
        Product product=new Product();
        product.setTitle(requestDTO.getTitle());
        product.setPrice(requestDTO.getPrice());
        product.setDescription(requestDTO.getDescription());
        product.setImageUrl(requestDTO.getImage());

        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getCategory());

       Product savedProduct= productService.addProduct(product);
        return savedProduct;
    }


    @PatchMapping("/update/{id}")
public Product updateProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        Product product=new Product();
        product.setTitle(requestDTO.getTitle());
        product.setPrice(requestDTO.getPrice());
        product.setDescription(requestDTO.getDescription());
        product.setImageUrl(requestDTO.getImage());

        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getCategory());

        return new Product();
}


@PutMapping("/replace/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody RequestDTO requestDTO){
        if(requestDTO.getTitle()==null || requestDTO.getDescription()==null || requestDTO.getCategory()==null){
            return null;
        }
        return new Product();
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }

}
