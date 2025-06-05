package com.damian.springcloud.msvc.items;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${config.baseurl.endpoint.msvc-products}")
    private String Url;
   @Bean // se utiliza la anotación @Bean para registrar el WebClient.Builder como un bean en el contexto de la aplicación
   // de Spring, lo que permite inyectarlo en otros componentes de la aplicación.
   @LoadBalanced // se utiliza la anotación @LoadBalanced para habilitar el balanceo de carga en el WebClient.
   // Esto permite que el WebClient pueda realizar solicitudes a servicios registrados en un servidor de descubrimiento
   // como Eureka o Consul, y distribuir las solicitudes entre las instancias disponibles de esos servicios.
   // Esto es útil en arquitecturas de microservicios donde se tienen múltiples instancias de un servicio y se desea
   // distribuir la carga entre ellas.
   // El WebClient es una alternativa al RestTemplate, y se utiliza para realizar solicitudes HTTP de manera asíncrona
   // y reactiva. Es parte del módulo Spring WebFlux, que es una biblioteca para construir aplicaciones web reactivas
   // en Spring.
    public WebClient.Builder webClientBuilder() {
    return WebClient.builder().baseUrl(this.Url); // se especifica la URL base del servicio al que se desea realizar las solicitudes.
    // se utilza el builder para crear instancias de WebClient
    //importar la dependencia de spring-boot-starter-webflux
    // en el pom.xml
}

}

