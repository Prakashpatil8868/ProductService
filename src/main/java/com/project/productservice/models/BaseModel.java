package com.project.productservice.models;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter

@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;

    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
