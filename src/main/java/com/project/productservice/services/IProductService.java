package com.project.productservice.services;

import com.project.productservice.DTO.RequestDTO;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import com.project.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product getSingleProduct(Long id) throws ProductNotFoundExceptions;
    public List<Product> getAllProducts();
public Product replaceProduct(Long id, RequestDTO requestDTO);
}
