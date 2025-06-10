package com.damian.springcloud.msvc.items.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.damian.springcloud.msvc.items.models.Items;
import com.damian.springcloud.msvc.items.services.ItemService;

@RestController
public class ItemController {
    // Aquí se pueden definir los endpoints para manejar las peticiones HTTP relacionadas con los items
    // Por ejemplo, un endpoint para obtener todos los items, otro para obtener un item por su ID, etc.
    // Se inyectaría el servicio de ItemsService para manejar la lógica de negocio
    private final ItemService itemService;
    public ItemController(@Qualifier("itemServiceWebClient") ItemService itemService) { //@Qualifier("itemServiceWebClient") se utiliza para especificar qué implementación de ItemService se debe inyectar
        // En este caso, se está inyectando la implementación ItemServiceWebClient, que es una implementación de ItemService que utiliza WebClient para realizar solicitudes HTTP.
        // Esto es útil cuando se tienen múltiples implementaciones de un servicio y se desea especificar cuál se debe utilizar en un controlador o componente específico.
        // Si no se especifica el @Qualifier, Spring utilizará la implementación predeterminada de ItemService. La inyeccion es el mismo nombre que la clase pero con la primera letra en minúscula
        // Por ejemplo, si la clase se llama ItemServiceWebClient, el nombre del bean será itemServiceWebClient.
        // Si se tiene una sola implementación de ItemService, no es necesario utilizar @Qualifier.
        this.itemService = itemService;
    }
    // @Autowired
    // private ItemService itemService;

     @GetMapping
     public List<Items> getAllItems() {
         List<Items> item = itemService.findAll();
         if (item.isEmpty()) {
             // Si la lista de items está vacía, se puede lanzar una excepción o retornar un mensaje de error
             // return ResponseEntity.status(404).body(Collections.singletonMap("message", "No hay items disponibles en el microservicio mvsc-products"));
             return Collections.emptyList(); // Retorna una lista vacía si no hay items
         }else {
             // Si la lista de items no está vacía, se retorna la lista de items
             return item;
         }
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getItemById(@PathVariable Long id) {
         Optional<Items> item = itemService.findById(id);
    if (item.isEmpty()) {
        // Si el item no se encuentra, se puede lanzar una excepción o retornar un mensaje de error
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "El item no se encuentra en el microservicio mvsc-products"));
    }
         // Si el item se encuentra, se retorna el item envuelto en un ResponseEntity
         // También se puede retornar un mensaje de error si el item no se encuentra
         // return ResponseEntity.ok(item.get());
         // O simplemente retornar el item directamente
         // return item.map(ResponseEntity::ok).orElseThrow(() -> new RuntimeException("El item no se encuentra"));
         // O retornar un mensaje de error si el item no se encuentra   
        return ResponseEntity.ok(item.get());
    }
}
