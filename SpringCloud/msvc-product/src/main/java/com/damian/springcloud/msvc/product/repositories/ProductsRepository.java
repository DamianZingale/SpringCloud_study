package com.damian.springcloud.msvc.product.repositories;

import org.springframework.data.repository.CrudRepository;

import com.damian.springcloud.msvc.product.entities.Products;
//ya por defecto es un componente de spring
//no lleva anotacion @Component
public interface ProductsRepository extends CrudRepository<Products, Long> {
    //es como un DAO. Hace las funciones de CRUD
    //no es necesario implementar nada, ya que el framework lo hace por nosotros
    //el CRUDRepository ya tiene implementados los metodos de CRUD
    //el primer parametro es el tipo de entidad, el segundo es el tipo de id
    //el id es el tipo de dato que se va a usar para el id de la entidad
    //es 100% declarativa
}
