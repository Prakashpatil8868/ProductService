package com.project.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @OneToMany (mappedBy = "category",cascade = {CascadeType.REMOVE})
    private List<Product> products;
private String name;
private String dyanamicPrice;
}
