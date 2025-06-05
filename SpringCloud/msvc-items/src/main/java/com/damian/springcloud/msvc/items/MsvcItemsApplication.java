package com.damian.springcloud.msvc.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients //Habilita el uso de Feign para llamar a otros microservicios
// Feign es una librería que permite hacer llamadas HTTP de manera sencilla y declarativa
// Con Feign, puedes definir interfaces que representan los servicios a los que quieres llamar
// y Spring Boot se encarga de generar la implementación en tiempo de ejecución
// Esta anotación permite que las interfaces Feign sean detectadas y utilizadas por Spring
@SpringBootApplication
public class MsvcItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcItemsApplication.class, args);
	}

}
