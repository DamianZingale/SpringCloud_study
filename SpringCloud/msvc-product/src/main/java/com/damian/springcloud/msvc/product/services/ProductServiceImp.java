package com.damian.springcloud.msvc.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damian.springcloud.msvc.product.entities.Products;
import com.damian.springcloud.msvc.product.repositories.ProductsRepository;

@Service //es un esteorito de componente pero aporta texto/ semantica. Podria anotarse con @component tambien.
public class ProductServiceImp implements ProductService {

    

    //inyeccion mediante constructor
    //en vez de usar @Autowired, se puede usar el constructor
    //en este caso, se inyecta el repositorio en el constructor
    //es una buena practica usar la inyeccion por constructor
    //ya que es mas facil de testear y es mas claro
    //tambien se puede usar @Autowired en el constructor
    //pero no es necesario
    // final para que no se pueda cambiar el valor de la variable
    //es una buena practica usar final para las variables que no se van a cambiar
    final private ProductsRepository repository; 
    //constructor
    public ProductServiceImp(ProductsRepository repository) {
        this.repository = repository;
    }
    @Override
    @Transactional(readOnly = true) //indica que el metodo es de solo lectura
    //si no se pone el readOnly, se hace un commit al final del metodo
    public List<Products> findAll() {
        //el findAll() devuelve un iterable, por lo que hay que convertirlo a una lista
        return (List<Products>) repository.findAll();
    }

    @Override
    public Optional<Products> findById(Long id) {
        return repository.findById(id);
    }

}
