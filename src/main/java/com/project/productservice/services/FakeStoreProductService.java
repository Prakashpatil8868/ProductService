package com.project.productservice.services;

import com.project.productservice.DTO.RequestDTO;
import com.project.productservice.DTO.ResponseDTO;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Primary
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements IProductService{
    RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
public Product getProductFromResponseDTO(ResponseDTO responseDTO){
      Product product=new Product();
      product.setId(responseDTO.getId());
      product.setTitle(responseDTO.getTitle());
      product.setDescription(responseDTO.getDescription());
      product.setPrice(responseDTO.getPrice());
      product.setCategory(new Category());
      product.getCategory().setName(responseDTO.getCategory());
      product.setImageUrl(responseDTO.getImageUrl());
      return product;
}
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundExceptions{
        ResponseDTO responseDTO =restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
             ResponseDTO.class
        );

        if(responseDTO==null){
            throw new ProductNotFoundExceptions("product with"+id+"does not exist");
        }
       Product product=getProductFromResponseDTO(responseDTO);
       return product;
    }
    @Override
    public List<Product> getAllProducts(){
      ResponseDTO[] responseList =restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
               ResponseDTO[].class
        );
      List<Product> products=new ArrayList<>();
      for(ResponseDTO responseDTO:responseList){
          products.add(getProductFromResponseDTO(responseDTO));
      }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO){
            RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDTO,ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(ResponseDTO.class,
                        restTemplate.getMessageConverters()
                        );
ResponseDTO responseDTO=restTemplate.execute(
        "https://fakestoreapi.com/products/" + id,
        HttpMethod.PUT,
        requestCallback,
        responseExtractor
);

        Product product=getProductFromResponseDTO(responseDTO);
        return product;
    }

}
