package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepository;
import ar.edu.utn.clientes.dao.ClienteDaoMemoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService service;

    @BeforeEach
    void setUp() {
        ClienteRepository repository =
                new ClienteRepository(
                        new ClienteDaoMemoria()
                );

        service = new ClienteService(repository);
    }

    @Test
    void obtenerClienteExistente() {
        ClienteDto cliente = service.obtenerCliente(1L)
                .orElseThrow();

        assertEquals("Ana", cliente.nombre());
    }

    @Test
    void obtenerClienteInexistente() {
        assertTrue(
                service.obtenerCliente(999L).isEmpty()
        );
    }
}