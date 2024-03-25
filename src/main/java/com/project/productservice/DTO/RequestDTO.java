package com.project.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}