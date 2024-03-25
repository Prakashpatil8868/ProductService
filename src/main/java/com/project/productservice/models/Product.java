package com.project.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String name;
    private String description;
    private double price;
    private String picture;

private String ImageUrl;
@ManyToOne
    private Category category;
}
