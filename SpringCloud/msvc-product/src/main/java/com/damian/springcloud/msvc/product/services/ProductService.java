package com.damian.springcloud.msvc.product.services;

import java.util.List;
import java.util.Optional;

import com.damian.springcloud.msvc.product.entities.Products;

public interface ProductService {
    //importar list de java.util
    List <Products> findAll();
    Optional<Products> findById(Long id);
}
