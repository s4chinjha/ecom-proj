package com.sachin.ecom_proj.repo;

import com.sachin.ecom_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//take two types:
//type of class/entity
//type of pk

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {


}
