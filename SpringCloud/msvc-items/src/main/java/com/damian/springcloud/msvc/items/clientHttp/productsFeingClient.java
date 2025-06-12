package com.damian.springcloud.msvc.items.clientHttp;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.damian.springcloud.msvc.items.models.Product;

@FeignClient(name = "msvc-product") // Aquí se define el cliente Feign para llamar al microservicio de productos 
// con Eureka seria solo el nombre del servicio.
// Feign es una librería que permite hacer llamadas HTTP de manera sencilla y declarativa
//Esta interfaz representa otro microservicio al que quiero llamar por HTTP, y quiero que me generes la implementación automáticamente
// name = "msvc-products", url = "http://localhost:8001/products"
public interface productsFeingClient {

    @GetMapping
    List<Product> findAll();

    @GetMapping("/{id}")
    public ResponseEntity<Product> details (@PathVariable Long id);
}
