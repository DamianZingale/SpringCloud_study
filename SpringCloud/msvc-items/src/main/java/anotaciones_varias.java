/*
  Feign es una herramienta que te permite hacer llamadas HTTP a otros servicios de forma muy simple y declarativa, como si estuvieras llamando un método de Java. Es súper útil en arquitecturas de microservicios, donde un servicio necesita comunicarse con otro.

📦 ¿Qué es Feign?
Feign es un cliente HTTP declarativo. Lo provee Spring Cloud OpenFeign y básicamente te permite escribir una interfaz en Java, anotar métodos, y listo: Spring se encarga de hacer la llamada HTTP por vos.

🧠 ¿Cómo funciona?
Imaginá que tenés dos microservicios:

Servicio A: necesita info de productos.

Servicio B: tiene un endpoint /productos.

Con Feign, en el Servicio A creás una interfaz que representa a Servicio B. No escribís lógica HTTP, ni RestTemplate, ni WebClient.



¿Qué es Spring WebFlux?
Es la alternativa reactiva a Spring MVC. Usa un modelo asíncrono y no bloqueante, ideal para apps que manejan muchas peticiones concurrentes (como APIs que escalan mucho, servidores de streaming, etc).

Usa internamente Project Reactor, que es una librería basada en el paradigma reactivo con los tipos principales: Mono y Flux.

🚀 ¿Qué significa "reactivo"?
En vez de que tu código bloquee un hilo mientras espera una respuesta (como en Spring MVC), en WebFlux no se bloquea nada. Se registran callbacks y el hilo queda libre hasta que haya una respuesta.

✅ Más rendimiento con menos recursos
✅ Perfecto para microservicios o I/O intensivo
⛔ Más complejo de leer/depurar si venís del mundo síncrono

🔄 Comparación rápida
Aspecto	Spring MVC	Spring WebFlux
Modelo	Síncrono (bloqueante)	Asíncrono (no bloqueante)
Librería	Servlet API	Project Reactor
Hilos por request	1 hilo por request	1 hilo puede manejar miles
Tipos devueltos	ResponseEntity, List	Mono, Flux
Escalabilidad	Limitada por hilos	Alta
  
Qué es Spring Cloud LoadBalancer?
Es una herramienta de balanceo de carga (load balancing) cliente-side incluida en Spring Cloud.

Es decir: permite que tu microservicio cliente elija de forma automática a qué instancia de otro microservicio conectarse, cuando hay varias disponibles.

🧠 ¿Para qué sirve?
Cuando trabajás con microservicios, es muy común tener varias instancias de un servicio (por ejemplo, para repartir la carga o tolerancia a fallos).

En vez de conectarte a una IP fija o a un solo host, el LoadBalancer elige dinámicamente una instancia de entre varias, como por ejemplo:

rust
Copiar
Editar
cliente -> producto-1
cliente -> producto-2
cliente -> producto-3
🧰 ¿Qué reemplaza?
Antes se usaba Ribbon (de Netflix), pero ya está deprecated. Ahora Spring recomienda usar:

✅ spring-cloud-loadbalancer

🔄 ¿Cómo funciona?
Cuando usás Feign Client, WebClient o RestTemplate anotado con @LoadBalanced, el cliente consulta el Service Discovery (como Eureka, Consul, etc.) y elige una instancia disponible.

¿Qué hace Spring Cloud LoadBalancer por vos?
Elige una instancia automáticamente (por defecto usa Round-Robin).

Se integra con Eureka, Consul o incluso sin Discovery (definiendo las instancias manualmente).

Se usa por detrás en Feign, WebClient, RestTemplate (si lo anotás con @LoadBalanced).


 */
