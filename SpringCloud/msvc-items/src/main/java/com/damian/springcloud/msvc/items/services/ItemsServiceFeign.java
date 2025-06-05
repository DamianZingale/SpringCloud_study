package com.damian.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.springcloud.msvc.items.clientHttp.productsFeingClient;
import com.damian.springcloud.msvc.items.models.Items;
import com.damian.springcloud.msvc.items.models.Product;

import feign.FeignException;

@Service
public class ItemsServiceFeign implements ItemService {

    // Aquí se inyectaría el cliente Feign para llamar al microservicio de productos
    @Autowired
    private productsFeingClient client;

    //Tambien se puede inyectar el cliente Feign directamente en el constructor
    // public ItemsServiceFeign(productsFeingClient productsFeingClient) {
    //     this.productsFeingClient = productsFeingClient;
    // }
    
    // Implementación de los métodos de la interfaz ItemService
   
    // Aquí se pueden agregar más métodos según sea necesario, como crear, actualizar o eliminar items
    @Override
    public List<Items> findAll() {
       
        return client.findAll().stream()
                .map(product -> new Items(product, new Random().nextInt(10) + 1)) // Asumiendo que el número de unidades es 1 por defecto
                .collect(Collectors.toList()); // Convertir a una lista de Items
    }  //Tambien se podria poner .ToList() al final del stream y evitar el collect

    @Override
    public Optional<Items> findById(Long id) {
        try {
            Product product = client.details(id).getBody();
            return Optional.of(new Items(product, new Random().nextInt(10) +1));
        } catch (FeignException e) {
            // Manejo de excepciones, por ejemplo, si el microservicio de productos no está disponible
            return Optional.empty(); // Retornar un Optional vacío en caso de error
        }
        
        }
}