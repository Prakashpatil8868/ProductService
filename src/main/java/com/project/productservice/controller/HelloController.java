package com.project.productservice.controller;

//import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String defaultHello(){
        return "default hello";
    }

    @GetMapping("/say/{yourName}/{yourCity}")
    public String sayHello(@PathVariable("yourName") String name, @PathVariable("yourCity")String city){
        return "Hey " + name + ", you are from " + city;
    }

    @GetMapping("/add/{a}/{b}")
    public String addNumbers(@PathVariable("a") Integer x, @PathVariable("b") Integer y) throws ProductNotFoundExceptions {
        throw new ProductNotFoundExceptions("sample exception in hello controller");
      //return String.valueOf(x + y);
    }
}