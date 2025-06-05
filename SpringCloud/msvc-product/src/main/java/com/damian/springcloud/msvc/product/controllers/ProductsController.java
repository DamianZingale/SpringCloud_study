package com.damian.springcloud.msvc.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damian.springcloud.msvc.product.entities.Products;
import com.damian.springcloud.msvc.product.services.ProductService;



@RestController // es un componente de tipo controlador
//@Controller trabaja con Thymeleaf
//@RequestMapping("/products") // se puede usar para definir la ruta base de todos los endpoints
// @CrossOrigin(origins = "http://localhost:8080") // permite el acceso a la API desde un origen diferente
@RequestMapping("/api/products") // define la ruta base de los endpoints de este controlador
public class ProductsController{

    final ProductService service;
    public ProductsController(ProductService service) {
        this.service = service;

    }
    
    @GetMapping
    public List<Products> list(){
       
        return this.service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <Products> details(@PathVariable Long id){ //ResponseEntity es una clase que permite devolver una respuesta HTTP
        // <?> o <T> es un tipo generico. Pude ser cualquier tipo de objeto incluso Products
        Optional<Products> productOptional = this.service.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow()); //orElseThrow() lanza una excepcion si el producto no existe
        }
        return ResponseEntity.notFound().build(); //bluild() construye la respuesta
                                                  //notFound() devuelve un 404
                                                
    }
    
    
}
