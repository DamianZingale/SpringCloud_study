package com.damian.springcloud.msvc.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.damian.springcloud.msvc.items.models.Items;
import com.damian.springcloud.msvc.items.models.Product;

//@Primary // se utiliza la anotación @Primary para indicar que este bean debe ser preferido cuando hay múltiples implementaciones del mismo tipo.
// En este caso, se está indicando que esta implementación de ItemService debe ser preferida sobre otras implementaciones
// cuando se inyecta el ItemService en otros componentes de la aplicación.
// Esto es útil cuando se tienen múltiples implementaciones de un servicio y se desea que una de ellas sea la predeterminada.
@Service
public class ItemServiceWebClient implements  ItemService{

    private final WebClient.Builder cliente;
    // El constructor recibe un WebClient.Builder que se utiliza para crear instancias de WebClient.
    // Este WebClient.Builder es inyectado por Spring, lo que permite que se configure automáticamente
    // con las propiedades definidas en la configuración de la aplicación, como la URL base del servicio,
    // los interceptores, los codificadores y decodificadores, etc.

    public ItemServiceWebClient(Builder cliente) {
        this.cliente = cliente;
    }

    @Override
    public List<Items> findAll() {
        try {
            return cliente.build() // se utiliza el método build() del WebClient.Builder para crear una instancia de WebClient.
                .get() // se utiliza el método get() para realizar una solicitud HTTP GET al servicio.
                // El método get() devuelve un objeto WebClient.RequestHeadersSpec que permite configurar la solicitud,
                // como establecer encabezados, parámetros de consulta, etc.
                .uri("http://msvc-products") // se utiliza el método uri() para especificar la URI del servicio al que se desea realizar la solicitud.
                // En este caso, se está utilizando una URL relativa que se resolverá en función de la configuración del WebClient.
                .accept(MediaType.APPLICATION_JSON)// se utiliza el método accept() para establecer el tipo de contenido que se espera recibir en la respuesta.
                // En este caso, se está indicando que se espera recibir una respuesta en formato JSON.
                .retrieve()// se utiliza el método retrieve() para enviar la solicitud y obtener una respuesta.
                // El método retrieve() devuelve un objeto WebClient.ResponseSpec que permite manejar la respuesta de manera reactiva.
                // Este objeto proporciona métodos para procesar la respuesta, como bodyToFlux(), bodyToMono(), etc.
                .bodyToFlux(Product.class)// se utiliza el método bodyToFlux() para indicar que se espera recibir una respuesta que contenga un flujo de elementos de tipo Items.
                // El método bodyToFlux() convierte la respuesta en un flujo reactivo de objetos Items.
                // Esto permite procesar la respuesta de manera asíncrona y reactiva, lo que es útil en aplicaciones que requieren alta concurrencia y rendimiento.
                .map(product -> new Items(product, new Random().nextInt(10) + 1)) 
                .collectList()// se utiliza el método collectList() para recopilar todos los elementos del flujo en una lista.
                // El método collectList() devuelve un Mono<List<Items>>, que es un tipo de objeto reactivo que representa una lista de elementos.
                // Un Mono es un tipo de objeto reactivo que representa una única emisión de un valor o un error.
                // En este caso, se está recopilando la lista de elementos Items.
                // El Mono<List<Items>> se puede suscribir para recibir la lista de elementos de manera asíncrona.
                // El método collectList() es útil cuando se espera recibir múltiples elementos y se desea procesarlos como una lista.
                .block(); // bloquea el hilo hasta que se complete la operación asíncrona
                    // El método block() se utiliza para esperar a que se complete la operación asíncrona y obtener el resultado.
                    // En este caso, se está bloqueando el hilo hasta que se reciba la lista de elementos Items.
                    // Es importante tener en cuenta que el uso de block() puede afectar la naturaleza reactiva del código,
                    // ya que bloquea el hilo hasta que se complete la operación. En aplicaciones reactivas, se recomienda evitar el uso de block() siempre que sea posible.
            
        } catch (WebClientResponseException e) {
           return List.of(); // En caso de error, se devuelve una lista vacía
        }
        
    }

    @Override
    public Optional<Items> findById(Long id) {
            Map<String, Long> param = new HashMap<>(); // se crea un mapa para almacenar los parámetros de la solicitud
            param.put("id", id); // se agrega el parámetro "id" al mapa con el valor del ID proporcionado
            // El método findById(Long id) busca un elemento por su ID utilizando el WebClient.
            // El método recibe un ID de tipo Long y devuelve un Optional<Items>, que es un contenedor que puede contener un objeto Items o estar vacío.
            // El uso de Optional permite manejar de manera segura el caso en el que no se encuentra un elemento con el ID proporcionado,
            // evitando así posibles NullPointerExceptions.
            try {
                return Optional.ofNullable( cliente.build() // se utiliza el método build() del WebClient.Builder para crear una instancia de WebClient.
                    .get() // se utiliza el método get() para realizar una solicitud HTTP GET al servicio.
                    .uri("http://msvc-products/{id}", param) // se utiliza el método uri() para especificar la URI del servicio al que se desea realizar la solicitud.
                    // En este caso, se está utilizando una URL relativa que incluye un parámetro de ruta {id} que se reemplazará con el valor del ID proporcionado.
                    // El mapa param se utiliza para pasar el valor del ID como parámetro de ruta.
                    .accept(MediaType.APPLICATION_JSON) // se utiliza el método accept() para establecer el tipo de contenido que se espera recibir en la respuesta.
                    // En este caso, se está indicando que se espera recibir una respuesta en formato JSON.
                    .retrieve() // se utiliza el método retrieve() para enviar la solicitud y obtener una respuesta.
                    // El método retrieve() devuelve un objeto WebClient.ResponseSpec que permite manejar la respuesta de manera reactiva.
                    // Este objeto proporciona métodos para procesar la respuesta, como bodyToMono(), bodyToFlux(), etc.        
                    .bodyToMono(Product.class) // se utiliza el método bodyToMono() para indicar que se espera recibir una respuesta que contenga un único elemento de tipo Items.
                    .map(product -> new Items(product, new Random().nextInt(10) + 1)) // se utiliza el método map() para transformar el objeto Product en un objeto Items.
                    // El método map() aplica una función de transformación al objeto Product y devuelve un objeto Items.
                    // En este caso, se está creando un nuevo objeto Items utilizando el objeto Product y un número aleatorio de unidades.
                    // El número aleatorio se genera utilizando la clase Random y se limita a un rango de 1 a 10.
                    .block());
                
            } catch (WebClientResponseException e) {
                return Optional.empty(); // En caso de error, se devuelve un Optional vacío
                // El método findById(Long id) busca un elemento por su ID utilizando el WebClient.
            }
            
    }



}
