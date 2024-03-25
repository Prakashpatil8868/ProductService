package com.project.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Long id;
    private String title;
    private double price;
    private  String description;
    private String category;
    private String ImageUrl;
}
