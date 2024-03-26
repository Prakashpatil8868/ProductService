package com.project.productservice.controller;

import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import com.project.productservice.services.SelfProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class RandomTests {
    @Autowired
    private SelfProductService selfProductService;

    @Test
    public void onePlusOne(){
        //arrange
        int a=10;
        int b=10;
        //act
        int x=a+b;

        //asert
        //assert x==20;
        //Assertions.assertTrue(x==30); give o/p as true & false
        Assertions.assertEquals(20,x);

        Assertions.assertTimeout(
                Duration.of(2L, ChronoUnit.SECONDS),
                ()-> selfProductService.getAllProducts()
        );

        Assertions.assertThrows(ProductNotFoundExceptions.class,
                () -> selfProductService.getSingleProduct(16L));



    }
}
