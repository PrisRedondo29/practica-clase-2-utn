package ar.edu.utn.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada Spring Boot.
 * Esta en el paquete raiz para que Spring descubra componentes
 * en todos los sub-paquetes (clientes.api.*).
 */
@SpringBootApplication
public class ClientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }
}